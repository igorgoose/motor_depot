package by.schepov.motordepot.specification.update.user;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.specification.update.UpdateSpecification;
import by.schepov.motordepot.specification.update.car.UpdateCarStateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUserBlockedSpecification implements UpdateSpecification<User> {

    private int id;
    private UserStatus status;
    private static final Logger LOGGER = LogManager.getLogger(UpdateCarStateSpecification.class);
    private static final String QUERY = "UPDATE motor_depot.users users " +
            "SET status_id = ? " +
            "WHERE users.id = ?";
    private ConnectionPool pool = ConnectionPool.INSTANCE;

    public UpdateUserBlockedSpecification(int id, UserStatus status){
        this.id = id;
        this.status = status;
    }

    @Override
    public void execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, status.getId());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }

}
