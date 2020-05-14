package by.schepov.motordepot.factory.service.impl;

import by.schepov.motordepot.factory.service.ServiceFactory;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.service.user.impl.UserRepositoryService;

public class RepositoryServiceFactory implements ServiceFactory {

    public static class InstanceHolder{
        public static final RepositoryServiceFactory INSTANCE = new RepositoryServiceFactory();
    }

    public static RepositoryServiceFactory getInstance(){
        return RepositoryServiceFactory.InstanceHolder.INSTANCE;
    }

    @Override
    public CarService createCarService() {
        return CarRepositoryService.getInstance();
    }

    @Override
    public UserService createUserService() {
        return UserRepositoryService.getInstance();
    }

    @Override
    public RequestService createRequestService() {
        return RequestRepositoryService.getInstance();
    }

    @Override
    public OrderService createOrderService() {
        return OrderRepositoryService.getInstance();
    }

}
