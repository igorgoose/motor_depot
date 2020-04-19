package by.schepov.motordepot.repository.impl.car;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.repository.Repository;
import by.schepov.motordepot.repository.impl.user.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public enum CarRepository implements Repository<Car> {
    INSTANCE;

    private ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(UserRepository.class);

    private static final String INSERT_QUERY =
            "INSERT INTO cars(driver_id, registration_number, name_id, load_capacity, passenger_capacity, status_id) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";

    @Override
    public void insert(Car item) throws RepositoryException {
        try(ProxyConnection connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, item.getDriver().getId());
            preparedStatement.setString(2, item.getRegistrationNumber());
            preparedStatement.setInt(3, item.getCarName().getId());
            preparedStatement.setInt(4, item.getLoadCapacity());
            preparedStatement.setInt(5, item.getPassengerCapacity());
            preparedStatement.setInt(6, item.getCarStatus().getId());
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(Car item) {
        throw new UnsupportedOperationException();
    }


}
