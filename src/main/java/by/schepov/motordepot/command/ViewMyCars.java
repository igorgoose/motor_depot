package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.CarServiceException;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewMyCars implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ViewMyCars.class);

    //todo create ServiceFactory
    private final CarService carService = CarRepositoryService.getInstance();

    ViewMyCars(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        try {
            Set<Car> cars = carService.getCarsByDriverId(user.getId());
            request.setAttribute(RequestAttribute.CARS.getName(), cars);
        } catch (CarServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.MANAGEMENT_CARS;
    }
}
