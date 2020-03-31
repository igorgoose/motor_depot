package by.schepov.motordepot.specification.impl.user;

import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.specification.Column;
import by.schepov.motordepot.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindUserByUsernameSpecification implements Specification<User> {

    private String login;
    private static final String QUERY =
            "SELECT users.id, login, password, role_id, email, is_blocked, role FROM motor_depot.users as users " +
                    "LEFT JOIN motor_depot.roles as roles on role_id = roles.id " +
                    "WHERE users.login = ?";
    private final ConnectionPool pool = ConnectionPool.INSTANCE;

    public FindUserByUsernameSpecification(String login) {
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
                        .withBlocked(Column.IS_BLOCKED)
                        .withEmail(Column.EMAIL)
                        .withLogin(Column.LOGIN)
                        .withPassword(Column.PASSWORD)
                        .withRole(Column.ROLE)
                        .build());
            }
            return users;
        } catch (ConnectionPoolException | SQLException e) {
            //todo log
            throw new SpecificationException(e);
        }
    }
}
