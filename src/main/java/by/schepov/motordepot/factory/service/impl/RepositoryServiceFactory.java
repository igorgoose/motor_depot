package by.schepov.motordepot.factory.service.impl;

import by.schepov.motordepot.factory.service.ServiceFactory;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarJDBCRepositoryService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderJDBCRepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestJDBCRepositoryService;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.service.user.impl.UserJDBCRepositoryService;

public class RepositoryServiceFactory implements ServiceFactory {

    private RepositoryServiceFactory(){}

    public static class InstanceHolder{
        public static final RepositoryServiceFactory INSTANCE = new RepositoryServiceFactory();
    }

    public static RepositoryServiceFactory getInstance(){
        return RepositoryServiceFactory.InstanceHolder.INSTANCE;
    }

    @Override
    public CarService createCarService() {
        return CarJDBCRepositoryService.getInstance();
    }

    @Override
    public UserService createUserService() {
        return UserJDBCRepositoryService.getInstance();
    }

    @Override
    public RequestService createRequestService() {
        return RequestJDBCRepositoryService.getInstance();
    }

    @Override
    public OrderService createOrderService() {
        return OrderJDBCRepositoryService.getInstance();
    }

}
