package by.schepov.motordepot.factory.repository;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.repository.Repository;

public interface RepositoryFactory {
    Repository<User> createUserRepository();
    Repository<Car> createCarRepository();
    Repository<Request> createRequestRepository();
    Repository<Order> createOrderRepository();
}
