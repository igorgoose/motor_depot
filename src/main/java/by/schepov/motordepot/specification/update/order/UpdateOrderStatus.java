package by.schepov.motordepot.specification.update.order;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.specification.query.impl.order.GetAllOrdersQuerySpecification;
import by.schepov.motordepot.specification.update.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateOrderStatus implements UpdateSpecification<Order> {

    private final boolean isComplete;
    private final int orderId;
    private static final Logger LOGGER = LogManager.getLogger(UpdateOrderStatus.class);
    private static final String QUERY = "UPDATE motor_depot.orders " +
                    "SET is_complete = ? " +
                    "WHERE orders.id = ?";
    private final ConnectionPool pool = ConnectionPool.INSTANCE;

    public UpdateOrderStatus(int orderId, boolean isComplete) {
        this.isComplete = isComplete;
        this.orderId = orderId;
    }

    @Override
    public void execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setBoolean(1, isComplete);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }
}
