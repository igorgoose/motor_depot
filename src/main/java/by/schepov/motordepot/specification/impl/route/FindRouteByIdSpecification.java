package by.schepov.motordepot.specification.impl.route;

import by.schepov.motordepot.builder.impl.route.ResultSetRouteBuilder;
import by.schepov.motordepot.entity.Route;
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

public class FindRouteByIdSpecification implements Specification<Route> {


    private int id;
    private static final Logger LOGGER = LogManager.getLogger(FindRouteByIdSpecification.class);
    private static final String QUERY = "SELECT * FROM routes WHERE id = ?";
    private final ConnectionPool pool = ConnectionPool.INSTANCE;

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
                routes.add(builder.withDepartureLocation(Column.DEPARTURE_LOCATION)
                        .withArrivalLocation(Column.ARRIVAL_LOCATION)
                        .withDepartureTime(Column.DEPARTURE_TIME)
                        .withArrivalTime(Column.ARRIVAL_TIME)
                        .build());
            }
            resultSet.close();
            return routes;
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }


}
