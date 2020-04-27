package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.CarServiceException;
import by.schepov.motordepot.exception.RequestValidatorException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import by.schepov.motordepot.validator.RequestValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Set;

public class AssignCar implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(AssignCar.class);

    //todo create ServiceFactory
    private final RequestService requestService = RequestRepositoryService.getInstance();
    private final CarService carService = CarRepositoryService.getInstance();

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if (user == null) {
            LOGGER.warn("Null user was provided by session!");
            return Page.HOME;
        }
        try{
            int carId = Integer.parseInt(request.getParameter(JSPParameter.CAR_ID.getName()));
            int requestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getName()));
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
            request.setAttribute(RequestAttribute.CAR.getName(), foundCar);
            request.setAttribute(RequestAttribute.REQUEST.getName(), foundRequest);
            return Page.SUBMIT_ORDER;
        } catch (RequestServiceException | CarServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
    }
}
