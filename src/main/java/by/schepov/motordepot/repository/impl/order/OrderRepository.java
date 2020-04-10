package by.schepov.motordepot.repository.impl.order;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum OrderRepository implements Repository<Order> {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(OrderRepository.class);

    private final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String INSERT_QUERY =
            "INSERT INTO orders(user_id, route_id, car_id, driver_id) VALUES(?, ?, ?, ?)";

    @Override
    public void insert(Order item) throws RepositoryException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, item.getUser().getId());
            preparedStatement.setInt(2, item.getRoute().getId());
            preparedStatement.setInt(3, item.getCar().getId());
            preparedStatement.setInt(4, item.getDriver().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.warn(e);
        }
    }



}
