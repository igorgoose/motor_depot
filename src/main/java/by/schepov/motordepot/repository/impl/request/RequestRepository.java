package by.schepov.motordepot.repository.impl.request;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.repository.Repository;
import by.schepov.motordepot.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public enum RequestRepository implements Repository<Request> {
    INSTANCE;

    private ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(RequestRepository.class);
    private static final String INSERT_QUERY = "INSERT INTO requests(user_id, route_id, passengers_quantity, load_kg) VALUES(?, ?, ?, ?)";

    @Override
    public void insert(Request item) throws RepositoryException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, item.getUser().getId());
            preparedStatement.setInt(2, item.getRoute().getId());
            preparedStatement.setInt(3, item.getPassengersQuantity());
            preparedStatement.setInt(4, item.getLoad());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new RepositoryException(e);
        }
    }

    @Override
    public Set<Request> execute(Specification<Request> query) throws RepositoryException {
        try {
            return query.execute();
        } catch (SpecificationException e) {
            LOGGER.warn(e);
            throw new RepositoryException(e);
        }
    }
}
