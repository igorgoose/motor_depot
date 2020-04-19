package by.schepov.motordepot.specification.update.car;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.specification.update.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCarStateSpecification implements UpdateSpecification<Car> {

    private int id;
    private CarStatus carStatus;
    private static final Logger LOGGER = LogManager.getLogger(UpdateCarStateSpecification.class);
    private static final String QUERY = "UPDATE motor_depot.cars " +
            "SET status_id = ? " +
            "WHERE cars.id = ?";
    private ConnectionPool pool = ConnectionPool.INSTANCE;

    public UpdateCarStateSpecification(int id, CarStatus carStatus) {
        this.id = id;
        this.carStatus = carStatus;
    }

    @Override
    public void execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, carStatus.getId());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }
}
