package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.CarServiceException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Set;

public class VerifyRequest implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ViewCars.class);

    //todo create ServiceFactory
    private final CarService carService = CarRepositoryService.getInstance();
    private final RequestService requestService = RequestRepositoryService.getInstance();

    VerifyRequest(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if (user == null) {
            LOGGER.warn("Null user was provided by session!");
            return Page.HOME;
        }
        try {
            int requestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getName()));
            Set<Request> requests = requestService.getRequestById(requestId);
            Iterator<Request> iterator = requests.iterator();
            Request foundRequest;
            if(iterator.hasNext()){
                foundRequest = iterator.next();
            } else {
                return Page.ERROR;
            }
            request.setAttribute(RequestAttribute.REQUEST.getName(), foundRequest);
            Set<Car> cars = carService.findFreeCars(foundRequest.getLoad(), foundRequest.getPassengersQuantity());
            request.setAttribute(RequestAttribute.CARS.getName(), cars);
        } catch (CarServiceException | RequestServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.REQUEST_VERIFICATION;
    }

}
