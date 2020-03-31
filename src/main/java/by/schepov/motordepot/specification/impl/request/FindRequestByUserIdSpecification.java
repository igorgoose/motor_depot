package by.schepov.motordepot.specification.impl.request;

import by.schepov.motordepot.builder.impl.request.ResultSetRequestBuilder;
import by.schepov.motordepot.builder.impl.route.ResultSetRouteBuilder;
import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.Route;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.specification.Column;
import by.schepov.motordepot.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindRequestByUserIdSpecification implements Specification<Request> {

    private int userId;

    private static final Logger LOGGER = LogManager.getLogger(FindRequestByUserIdSpecification.class);
    private static final String QUERY =
            "SELECT reqs.id, passengers_quantity, load_kg, " +
                    "user_id, login, password, role_id, email, is_blocked, " +
                    "route_id, departure_time, departure_location, arrival_time, arrival_location, role " +
                    "FROM motor_depot.requests as reqs " +
                    "LEFT JOIN motor_depot.users as users on user_id = users.id " +
                    "LEFT JOIN motor_depot.routes as routes on route_id = routes.id " +
                    "LEFT JOIN motor_depot.roles as roles on role_id = roles.id " +
                    "WHERE user_id = ?";
    private final ConnectionPool pool = ConnectionPool.INSTANCE;

    public FindRequestByUserIdSpecification(int userId){
        this.userId = userId;
    }

    @Override
    public Set<Request> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, userId);
            //todo resolve code duplication issue
            ResultSet resultSet = preparedStatement.executeQuery();
            HashSet<Request> requests = new HashSet<>();
            ResultSetRouteBuilder routeBuilder = new ResultSetRouteBuilder(resultSet);
            ResultSetUserBuilder userBuilder = new ResultSetUserBuilder(resultSet);
            ResultSetRequestBuilder requestBuilder = new ResultSetRequestBuilder(resultSet);
            User user;
            Route route;
            while (resultSet.next()) {
                userBuilder.reset();
                routeBuilder.reset();
                user = userBuilder.withId(Column.USER_ID)
                        .withLogin(Column.LOGIN)
                        .withPassword(Column.PASSWORD)
                        .withRole(Column.ROLE)
                        .withEmail(Column.EMAIL)
                        .withBlocked(Column.IS_BLOCKED)
                        .build();
                route = routeBuilder.withId(Column.ROUTE_ID)
                        .withArrivalLocation(Column.ARRIVAL_LOCATION)
                        .withArrivalTime(Column.ARRIVAL_TIME)
                        .withDepartureLocation(Column.DEPARTURE_LOCATION)
                        .withDepartureTime(Column.DEPARTURE_TIME)
                        .build();
                requestBuilder.reset();
                requests.add(requestBuilder.withId(Column.ID)
                .withRoute(route)
                .withUser(user)
                .withLoad(Column.LOAD_KG)
                .withPassengersQuantity(Column.PASSENGERS_QUANTITY)
                .build());
            }
            return requests;
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }
}
