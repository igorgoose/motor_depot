package by.schepov.motordepot.repository;

import by.schepov.motordepot.pool.ConnectionPool;

public abstract class JDBCRepository<T> implements Repository<T>{

    protected final ConnectionPool pool = ConnectionPool.INSTANCE;

}
