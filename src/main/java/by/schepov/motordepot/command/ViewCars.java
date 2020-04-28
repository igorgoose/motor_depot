package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.CarServiceException;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewCars implements Executable{

    private static final Logger LOGGER = LogManager.getLogger(ViewCars.class);

    //todo create ServiceFactory
    private final CarService carService = CarRepositoryService.getInstance();

    ViewCars(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Set<Car> cars = carService.getAllCars();
            request.setAttribute(RequestAttribute.CARS.getName(), cars);
        } catch (CarServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.MANAGEMENT_CARS;
    }

}
