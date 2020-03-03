package by.schepov.motordepot.pool;


import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import org.apache.log4j.Logger;

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

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static final String DB_PROPERTIES = "db.properties";
    private static final int CAPACITY = 32;
    private static final int TIMEOUT_LIMIT = 5;
    public static final String DB_PROPERTY_URL_KEY = "url";
    private final BlockingQueue<ProxyConnection> availableConnections = new ArrayBlockingQueue<>(CAPACITY);
    private final List<ProxyConnection> unavailableConnections = new LinkedList<>();
    private final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private final Properties dbProperties = new Properties();
    private String db_url;

    public void initializePool() throws ConnectionPoolException {
        initializeProperties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (!isInitialized.get()) {
                for (int i = 0; i < CAPACITY; i++) {
                    availableConnections.add(new ProxyConnection(DriverManager.getConnection(db_url, dbProperties)));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {//todo log
            throw new ConnectionPoolException(e);
        }
        isInitialized.set(true);
    }

    public ProxyConnection getConnection() throws ConnectionPoolException {
        if(!isInitialized.get()){
            throw new ConnectionPoolException("Connection pool is not initialized");
        }
        ProxyConnection connection = null;
        try {
            if ((connection = availableConnections.poll(TIMEOUT_LIMIT, TimeUnit.SECONDS)) != null) {
                unavailableConnections.add(connection);
            }
        } catch (InterruptedException e) {//todo log
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void returnConnection(ProxyConnection connection) throws ConnectionPoolException {
        if(connection == null){
            throw new ConnectionPoolException("Null connection passed");
        }
        try {
            connection.setAutoCommit(true);
            if(unavailableConnections.remove(connection)){
                availableConnections.put(connection);
            }//throw?
        } catch (InterruptedException e) {//todo log
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Failed to return connection", e);
        }
    }

    public void closePool(){
        ProxyConnection connection;
        while(!availableConnections.isEmpty()){
            connection = availableConnections.poll();
            if(connection != null){
                connection.closeInPool();
            }
        }
        while(!unavailableConnections.isEmpty()){
            unavailableConnections.remove(0).closeInPool();//multithreading?
        }
    }

    private void initializeProperties() throws ConnectionPoolException {
        InputStream propertiesInputStream = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES);
        if(propertiesInputStream != null) {
            try {
                dbProperties.load(propertiesInputStream);
                db_url = dbProperties.getProperty(DB_PROPERTY_URL_KEY);
            } catch (IOException e) {//todo log
                throw new ConnectionPoolException(e);
            }
        }
    }

}
