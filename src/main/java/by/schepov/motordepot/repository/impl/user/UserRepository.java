package by.schepov.motordepot.repository.impl.user;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum UserRepository implements Repository<User> {
    INSTANCE;

    private ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(UserRepository.class);

    private static final String INSERT_QUERY =
            "INSERT INTO users(login, password, role_id, email, status_id) VALUES(?, ?, ?, ?, ?)";

    @Override
    public void insert(User item) throws RepositoryException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, item.getUsername());
            preparedStatement.setString(2, item.getPassword());
            preparedStatement.setInt(3, item.getRole().getId());
            preparedStatement.setString(4, item.getEmail());
            preparedStatement.setBoolean(5, item.isBlocked());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new RepositoryException(e);
        }
    }

}
