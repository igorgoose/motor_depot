package by.schepov.motordepot.specification.query.impl.request;

import by.schepov.motordepot.builder.impl.request.ResultSetRequestBuilder;
import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.Request;
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
import java.util.LinkedHashSet;
import java.util.Set;

public class GetAllRequestsQuerySpecification implements QuerySpecification<Request> {

    private static final Logger LOGGER = LogManager.getLogger(GetAllRequestsQuerySpecification.class);
    private static final String QUERY =
            "SELECT reqs.id, passengers_quantity, load_capacity, departure_location, arrival_location," +
                    "user_id, login, password, role_id, email, ust.status user_status, role " +
                    "FROM motor_depot.requests as reqs " +
                    "LEFT JOIN motor_depot.users as users on user_id = users.id " +
                    "LEFT JOIN motor_depot.roles as roles on role_id = roles.id " +
                    "LEFT JOIN motor_depot.user_statuses ust on users.status_id = ust.id ";

    private final ConnectionPool pool = ConnectionPool.INSTANCE;

    @Override
    public Set<Request> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            //todo resolve code duplication issue
            ResultSet resultSet = preparedStatement.executeQuery();
            LinkedHashSet<Request> requests = new LinkedHashSet<>();
            ResultSetUserBuilder userBuilder = new ResultSetUserBuilder(resultSet);
            ResultSetRequestBuilder requestBuilder = new ResultSetRequestBuilder(resultSet);
            User user;
            while (resultSet.next()) {
                userBuilder.reset();
                user = userBuilder.withId(Column.USER_ID)
                        .withLogin(Column.LOGIN)
                        .withPassword(Column.PASSWORD)
                        .withRole(Column.ROLE)
                        .withEmail(Column.EMAIL)
                        .withStatus(Column.USER_STATUS)
                        .build();
                requestBuilder.reset();
                requests.add(requestBuilder.withId(Column.ID)
                        .withDepartureLocation(Column.DEPARTURE_LOCATION)
                        .withArrivalLocation(Column.ARRIVAL_LOCATION)
                        .withUser(user)
                        .withLoad(Column.LOAD_CAPACITY)
                        .withPassengersQuantity(Column.PASSENGERS_QUANTITY)
                        .build());
            }
            resultSet.close();
            return requests;
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }
}
