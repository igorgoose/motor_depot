package by.schepov.motordepot.repository.impl.route;

import by.schepov.motordepot.entity.Route;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum RouteRepository implements Repository<Route>{
    INSTANCE;

    private ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(RouteRepository.class);

    private static final String INSERT_QUERY =
            "INSERT INTO routes(departure_time, arrival_time, departure_location, arrival_location) VALUES(?, ?, ?, ?)";

    @Override
    public void insert(Route item) throws RepositoryException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setDate(1, item.getDepartureTime());
            preparedStatement.setDate(2, item.getArrivalTime());
            preparedStatement.setString(3, item.getDepartureLocation());
            preparedStatement.setString(4, item.getArrivalLocation());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new RepositoryException(e);
        }
    }

}
