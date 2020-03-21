package by.schepov.motordepot.specification.impl.route;

import by.schepov.motordepot.builder.impl.route.ResultSetRouteBuilder;
import by.schepov.motordepot.entity.Route;
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

public class FindRouteByIdSpecification implements Specification<Route> {


    private int id;
    private static final String QUERY = "SELECT * FROM routes WHERE id = ?";
    private final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String DEPARTURE_LOCATION_COLUMN = "departure_location";
    private static final String ARRIVAL_LOCATION_COLUMN = "arrival_location";
    private static final String DEPARTURE_TIME_COLUMN = "departure_time";
    private static final String ARRIVAL_TIME_COLUMN = "arrival_time";

    public FindRouteByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public Set<Route> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            HashSet<Route> routes = new HashSet<>();
            ResultSetRouteBuilder builder = new ResultSetRouteBuilder(resultSet);
            while (resultSet.next()) {
                builder.reset();
                routes.add(builder.withDepartureLocation(DEPARTURE_LOCATION_COLUMN)
                        .withArrivalLocation(ARRIVAL_LOCATION_COLUMN)
                        .withDepartureTime(DEPARTURE_TIME_COLUMN)
                        .withArrivalTime(ARRIVAL_TIME_COLUMN)
                        .build());
            }
            return routes;
        } catch (ConnectionPoolException | SQLException e) {
            //todo log
            throw new SpecificationException(e);
        }
    }


}
