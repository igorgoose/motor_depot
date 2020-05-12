package by.schepov.motordepot.command;

import by.schepov.motordepot.builder.impl.request.RequestBuilder;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.parameter.*;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateRequest implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(CreateRequest.class);
    private final RequestService requestService = RequestRepositoryService.getInstance();
    private static final String BUNDLE_NAME = "locale";

    CreateRequest(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());

        try {
            String departureLocation = request.getParameter(JSPParameter.DEPARTURE_LOCATION.getName());
            String arrivalLocation = request.getParameter(JSPParameter.ARRIVAL_LOCATION.getName());
            int passengers = SelectOption.valueOf(request.getParameter(JSPParameter.PASSENGER_QUANTITY.getName())).getValue();
            int loadVolume = SelectOption.valueOf(request.getParameter(JSPParameter.LOAD_VOLUME.getName())).getValue();
            RequestBuilder requestBuilder = new RequestBuilder();
            requestBuilder.reset();
            Request userRequest = requestBuilder.withUser(user).withDepartureLocation(departureLocation)
                    .withPassengerQuantity(passengers).withArrivalLocation(arrivalLocation).withLoad(loadVolume).build();
            requestService.insertRequest(userRequest);
        } catch (RequestServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request, e.getMessageBundleKey());
            }
            return Page.AUTHORIZE;
        }
        return Page.HOME;
    }

    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }
}
