package by.schepov.motordepot.factory.repository.impl;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.factory.repository.RepositoryFactory;
import by.schepov.motordepot.factory.service.impl.RepositoryServiceFactory;
import by.schepov.motordepot.repository.Repository;
import by.schepov.motordepot.repository.impl.car.CarJDBCRepository;
import by.schepov.motordepot.repository.impl.order.OrderJDBCRepository;
import by.schepov.motordepot.repository.impl.request.RequestJDBCRepository;
import by.schepov.motordepot.repository.impl.user.UserJDBCRepository;

public class JDBCRepositoryFactory implements RepositoryFactory {

    private JDBCRepositoryFactory(){}

    public static class InstanceHolder{
        public static final JDBCRepositoryFactory INSTANCE = new JDBCRepositoryFactory();
    }

    public static JDBCRepositoryFactory getInstance(){
        return JDBCRepositoryFactory.InstanceHolder.INSTANCE;
    }

    @Override
    public Repository<User> createUserRepository() {
        return UserJDBCRepository.getInstance();
    }

    @Override
    public Repository<Car> createCarRepository() {
        return CarJDBCRepository.getInstance();
    }

    @Override
    public Repository<Request> createRequestRepository() {
        return RequestJDBCRepository.getInstance();
    }

    @Override
    public Repository<Order> createOrderRepository() {
        return OrderJDBCRepository.getInstance();
    }
}
