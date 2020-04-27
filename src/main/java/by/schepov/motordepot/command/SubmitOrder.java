package by.schepov.motordepot.command;

import by.schepov.motordepot.builder.impl.order.OrderBuilder;
import by.schepov.motordepot.entity.*;
import by.schepov.motordepot.exception.CarServiceException;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Set;

public class SubmitOrder implements Executable{

    private static final Logger LOGGER = LogManager.getLogger(SubmitOrder.class);

    //todo create ServiceFactory
    private final RequestService requestService = RequestRepositoryService.getInstance();
    private final OrderService orderService = OrderRepositoryService.getInstance();
    private final CarService carService = CarRepositoryService.getInstance();
    private final OrderBuilder orderBuilder = new OrderBuilder();

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if (user == null) {
            LOGGER.warn("Null user was provided by session!");
            return Page.HOME;
        }
        try{
            int requestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getName()));
            int carId = Integer.parseInt(request.getParameter(JSPParameter.CAR_ID.getName()));
            Request foundRequest = requestService.getRequestById(requestId);
            if(foundRequest == null) {
                LOGGER.warn("Request hasn't been found(id=" + requestId + ")");
                return Page.ERROR;
            }
            Car foundCar = carService.findCarById(carId);
            if(foundCar == null){
                LOGGER.warn("Car hasn't been found(id=" + carId + ")");
                return Page.ERROR;
            }
            if(foundCar.getCarStatus() == CarStatus.BUSY){
                LOGGER.info("The car was assigned by another admin(car_id=" + carId + ")");
                request.setAttribute(RequestAttribute.REQUEST.getName(), foundRequest);
                Set<Car> cars = carService.findFreeCars(foundRequest.getLoad(), foundRequest.getPassengersQuantity());
                request.setAttribute(RequestAttribute.CARS.getName(), cars);
                return Page.REQUEST_VERIFICATION;
            }
            orderBuilder.reset();
            Order order = orderBuilder.withUser(foundRequest.getUser()).withCar(foundCar).withDriver(foundCar.getDriver())
                    .withDepartureLocation(foundRequest.getDepartureLocation()).withArrivalLocation(foundRequest.getArrivalLocation())
                    .build();
            carService.updateCarStatus(carId, CarStatus.BUSY);
            requestService.deleteRequest(foundRequest);
            orderService.insertOrder(order);
            return Page.MANAGEMENT;
        } catch (RequestServiceException | CarServiceException | OrderServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
    }

}
