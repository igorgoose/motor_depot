package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.RepositoryAction;
import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.service.CarServiceException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.request.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssignCar extends RepositoryAction {

    private static final Logger LOGGER = LogManager.getLogger(AssignCar.class);

    private final RequestService requestService = serviceFactory.createRequestService();
    private final CarService carService = serviceFactory.createCarService();
    private static final String BUNDLE_NAME = "locale";

    public AssignCar() {

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            int carId = Integer.parseInt(request.getParameter(JSPParameter.CAR_ID.getName()));
            int requestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getName()));
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
            request.setAttribute(RequestAttribute.CAR.getName(), foundCar);
            request.setAttribute(RequestAttribute.REQUEST.getName(), foundRequest);
            return Page.SUBMIT_ORDER;
        } catch (RequestServiceException | CarServiceException e) {
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
