package by.schepov.motordepot.repository.impl.request;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.repository.JDBCRepository;
import by.schepov.motordepot.specification.query.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class RequestJDBCRepository extends JDBCRepository<Request> {


    private static final Logger LOGGER = LogManager.getLogger(RequestJDBCRepository.class);
    private static final String INSERT_QUERY = "INSERT INTO motor_depot.requests(user_id, departure_location, arrival_location, passengers_quantity, load_capacity)" +
            " VALUES(?, ?, ?, ?, ?)";
    public static final String DELETE_QUERY = "DELETE FROM motor_depot.requests WHERE id = ?";

    private RequestJDBCRepository() {

    }

    public static class InstanceHolder {
        public static final RequestJDBCRepository INSTANCE = new RequestJDBCRepository();
    }

    public static RequestJDBCRepository getInstance() {
        return RequestJDBCRepository.InstanceHolder.INSTANCE;
    }

    @Override
    public void insert(Request item) throws RepositoryException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, item.getUser().getId());
            preparedStatement.setString(2, item.getDepartureLocation());
            preparedStatement.setString(3, item.getArrivalLocation());
            preparedStatement.setInt(4, item.getPassengersQuantity());
            preparedStatement.setInt(5, item.getLoad());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(Request item) throws RepositoryException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, item.getId());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new RepositoryException(e);
        }
    }

    @Override
    public Set<Request> executeQuery(QuerySpecification<Request> query) throws RepositoryException {
        try {
            return query.execute();
        } catch (SpecificationException e) {
            LOGGER.warn(e);
            throw new RepositoryException(e);
        }
    }
}
