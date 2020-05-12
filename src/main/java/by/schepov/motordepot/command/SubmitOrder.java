package by.schepov.motordepot.command;

import by.schepov.motordepot.builder.impl.order.OrderBuilder;
import by.schepov.motordepot.entity.*;
import by.schepov.motordepot.exception.service.CarServiceException;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.service.user.impl.UserRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class SubmitOrder implements Executable{

    private static final Logger LOGGER = LogManager.getLogger(SubmitOrder.class);

    //todo create ServiceFactory
    private final RequestService requestService = RequestRepositoryService.getInstance();
    private final OrderService orderService = OrderRepositoryService.getInstance();
    private final CarService carService = CarRepositoryService.getInstance();
    private final UserService userService = UserRepositoryService.getInstance();
    private final OrderBuilder orderBuilder = new OrderBuilder();
    private static final String BUNDLE_NAME = "locale";

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            int requestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getName()));
            int carId = Integer.parseInt(request.getParameter(JSPParameter.CAR_ID.getName()));
            Request foundRequest = requestService.getRequestById(requestId);
            if(foundRequest == null) {
                LOGGER.warn("Request hasn't been found(id=" + requestId + ")");
                setMessage(request, MessageKey.UNEXPECTED_ERROR);
                return Page.ERROR;
            }
            Car foundCar = carService.findCarById(carId);
            if(foundCar == null){
                LOGGER.warn("Car hasn't been found(id=" + carId + ")");
                setMessage(request, MessageKey.UNEXPECTED_ERROR);
                return Page.ERROR;
            }
            if (foundCar.getCarStatus() == CarStatus.BUSY) {
                LOGGER.info("The car was assigned by another admin(car_id=" + carId + ")");
                request.setAttribute(RequestAttribute.REQUEST.getName(), foundRequest);
                Set<Car> cars = carService.findFreeCars(foundRequest.getLoad(), foundRequest.getPassengersQuantity());
                request.setAttribute(RequestAttribute.CARS.getName(), cars);
                setMessage(request, MessageKey.CAR_IS_BUSY);
                return Page.REQUEST_VERIFICATION;
            } else if (foundCar.getDriver().getStatus().getId() > UserStatus.ACTIVE.getId()) {
                LOGGER.info("The driver is busy(driver_id=" + foundCar.getDriver().getStatus().getId() + ")");
                request.setAttribute(RequestAttribute.REQUEST.getName(), foundRequest);
                Set<Car> cars = carService.findFreeCars(foundRequest.getLoad(), foundRequest.getPassengersQuantity());
                request.setAttribute(RequestAttribute.CARS.getName(), cars);
                setMessage(request, MessageKey.DRIVER_IS_BUSY_OR_BLOCKED);
                return Page.REQUEST_VERIFICATION;
            }
            orderBuilder.reset();
            Order order = orderBuilder.withUser(foundRequest.getUser()).withCar(foundCar)
                    .withDepartureLocation(foundRequest.getDepartureLocation()).withArrivalLocation(foundRequest.getArrivalLocation())
                    .build();
            carService.updateCarStatus(carId, CarStatus.BUSY);
            userService.updateStatusById(foundCar.getDriver().getId(), UserStatus.BUSY);
            requestService.deleteRequest(foundRequest);
            orderService.insertOrder(order);
            return Page.MANAGEMENT;
        } catch (RequestServiceException | UserServiceException | OrderServiceException | CarServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request, e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
    }


    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }

}
