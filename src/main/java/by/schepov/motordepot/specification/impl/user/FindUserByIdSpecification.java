package by.schepov.motordepot.specification.impl.user;

import by.schepov.motordepot.builder.impl.route.ResultSetRouteBuilder;
import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.Route;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindUserByIdSpecification implements Specification<User> {

    private int id;
    private static final String QUERY = "SELECT * FROM users WHERE id = ?";
    private final ConnectionPool pool = ConnectionPool.INSTANCE;
    public static final String ID_COLUMN = "id";
    public static final String BLOCKED_COLUMN = "is_blocked";
    public static final String EMAIL_COLUMN = "email";
    public static final String LOGIN_COLUMN = "login";
    public static final String PASSWORD_COLUMN = "password";

    public FindUserByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public Set<User> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            HashSet<User> users = new HashSet<>();
            ResultSetUserBuilder builder = new ResultSetUserBuilder(resultSet);
            while (resultSet.next()) {
                builder.reset();
                users.add(builder.withId(ID_COLUMN)
                        .withBlocked(BLOCKED_COLUMN)
                        .withEmail(EMAIL_COLUMN)
                        .withLogin(LOGIN_COLUMN)
                        .withPassword(PASSWORD_COLUMN)
                        .build());
            }
            return users;
        } catch (ConnectionPoolException | SQLException e) {
            //todo log
            throw new SpecificationException(e);
        }
    }
}
