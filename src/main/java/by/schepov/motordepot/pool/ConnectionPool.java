package by.schepov.motordepot.pool;


import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public enum ConnectionPool {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final String DB_PROPERTIES = "db.properties";
    private static final int CAPACITY = 32;
    private static final int TIMEOUT_LIMIT = 10;
    private static final String DB_PROPERTY_URL_KEY = "url";
    private final BlockingQueue<ProxyConnection> availableConnections = new ArrayBlockingQueue<>(CAPACITY);
    private final List<ProxyConnection> unavailableConnections = new LinkedList<>();
    private final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private final Properties dbProperties = new Properties();
    private String dbURL;

    public void initializePool() throws ConnectionPoolException {//init block?
        initializeProperties();
        try {
            DriverManager.registerDriver(new Driver());
            if (!isInitialized.get()) {
                for (int i = 0; i < CAPACITY; i++) {
                    availableConnections.add(new ProxyConnection(DriverManager.getConnection(dbURL, dbProperties)));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to initialize connection pool", e);
            throw new ConnectionPoolException(e);
        }
        isInitialized.set(true);
    }

    public ProxyConnection getConnection() throws ConnectionPoolException {
        if (!isInitialized.get()) {
            throw new ConnectionPoolException("Connection pool is not initialized");
        }
        ProxyConnection connection = null;
        try {
            if ((connection = availableConnections.poll(TIMEOUT_LIMIT, TimeUnit.SECONDS)) != null) {
                unavailableConnections.add(connection);
            }
        } catch (InterruptedException e) {
            LOGGER.warn("Failed to get connection", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    void returnConnection(ProxyConnection connection) throws ConnectionPoolException {//default?
        if (connection == null) {
            throw new ConnectionPoolException("Null connection passed");
        }
        try {
            connection.setAutoCommit(true);
            if (unavailableConnections.remove(connection)) {
                availableConnections.put(connection);
            } else {
                throw new ConnectionPoolException("Unknown connection passed");
            }
        } catch (InterruptedException e) {
            LOGGER.warn("Failed to return connection", e);
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Failed to return connection", e);
        }
    }


    public void closePool() throws ConnectionPoolException {
        try {
            for (int i = 0; i < CAPACITY; i++) {
                availableConnections.take().closeInPool();
            }
        } catch (InterruptedException e) {
            LOGGER.warn("Failed to close connection pool", e);
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            LOGGER.warn("Failed to close connection pool", e);
            throw new ConnectionPoolException(e);
        }
    }

    private void initializeProperties() throws ConnectionPoolException {
        InputStream propertiesInputStream = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES);
        if (propertiesInputStream != null) {
            try {
                dbProperties.load(propertiesInputStream);
                dbURL = dbProperties.getProperty(DB_PROPERTY_URL_KEY);
            } catch (IOException e) {
                LOGGER.error("Failed to load database properties");
                throw new ConnectionPoolException(e);
            }
        } else {
            throw new ConnectionPoolException("Failed to find database properties file");
        }
    }

}
