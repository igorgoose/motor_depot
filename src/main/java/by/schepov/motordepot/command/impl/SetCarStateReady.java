package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.RepositoryAction;
import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.CarServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.SessionAttribute;
import by.schepov.motordepot.service.car.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class SetCarStateReady extends RepositoryAction {

    private static final Logger LOGGER = LogManager.getLogger(ViewMyCars.class);
    private static final String BUNDLE_NAME = "locale";

    private final CarService carService = serviceFactory.createCarService();

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        try {
            int carId = Integer.parseInt(request.getParameter(JSPParameter.CAR_ID.getName()));
            carService.updateCarStatus(carId, CarStatus.READY);
            Set<Car> cars = carService.getCarsByDriverId(user.getId());
            request.getSession().setAttribute(SessionAttribute.CARS.getName(), cars);
        } catch (CarServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request, e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
        return Page.MANAGEMENT_CARS;
    }

    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }

}
