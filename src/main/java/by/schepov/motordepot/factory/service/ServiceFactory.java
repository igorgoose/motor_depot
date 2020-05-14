package by.schepov.motordepot.factory.service;

import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.user.UserService;

public interface ServiceFactory {
    CarService createCarService();
    UserService createUserService();
    RequestService createRequestService();
    OrderService createOrderService();
}
