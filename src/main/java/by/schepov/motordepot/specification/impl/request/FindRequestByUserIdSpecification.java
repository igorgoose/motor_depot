package by.schepov.motordepot.specification.impl.request;

import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.Request;
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

public class FindRequestByUserIdSpecification implements Specification<Request> {

    private int userId;
    private int routeId;

    public static final String QUERY =
            "SELECT reqs.id, user_id, route_id, passengers_quantity, load_kg FROM motor_depot.requests as reqs " +
                    "LEFT JOIN motor_depot.users as users on user_id = users.id " +
                    "LEFT JOIN motor_depot.routes as routes on route_id = routes.id " +
                    "WHERE user_id = ?";
    private final ConnectionPool pool = ConnectionPool.INSTANCE;

    public FindRequestByUserIdSpecification(int userId){
        this.userId = userId;

    }

    @Override
    public Set<Request> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, login);
            //todo resolve code duplication issue
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
                        .withRole(ROLE_COLUMN)
                        .build());
            }
            return users;
        } catch (ConnectionPoolException | SQLException e) {
            //todo log
            throw new SpecificationException(e);
        }
    }
}
