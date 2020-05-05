package by.schepov.motordepot.specification.query.impl.user;

import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.specification.Column;
import by.schepov.motordepot.specification.query.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindUserByUsernameQuerySpecification implements QuerySpecification<User> {

    private String login;
    private static final Logger LOGGER = LogManager.getLogger(FindUserByUsernameQuerySpecification.class);
    private static final String QUERY =
            "SELECT users.id, login, password, role_id, email, status user_status, role FROM motor_depot.users users " +
                    "LEFT JOIN motor_depot.roles roles on role_id = roles.id " +
                    "LEFT JOIN motor_depot.user_statuses st on st.id = users.status_id " +
                    "WHERE users.login = ?";
    private final ConnectionPool pool = ConnectionPool.INSTANCE;

    public FindUserByUsernameQuerySpecification(String login) {
        this.login = login;
    }


    @Override
    public Set<User> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, login);
            //todo resolve code duplication issue
            ResultSet resultSet = preparedStatement.executeQuery();
            HashSet<User> users = new HashSet<>();
            ResultSetUserBuilder builder = new ResultSetUserBuilder(resultSet);
            while (resultSet.next()) {
                builder.reset();
                users.add(builder.withId(Column.ID)
                        .withStatus(Column.USER_STATUS)
                        .withEmail(Column.EMAIL)
                        .withLogin(Column.LOGIN)
                        .withPassword(Column.PASSWORD)
                        .withRole(Column.ROLE)
                        .build());
            }
            resultSet.close();
            return users;
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }
}
