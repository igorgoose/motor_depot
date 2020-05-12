package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewRequests implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ViewRequests.class);
    private static final String BUNDLE_NAME = "locale";

    private final RequestRepositoryService requestService = RequestRepositoryService.getInstance();

    ViewRequests(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Set<Request> requests = requestService.getAllRequests();
            request.setAttribute(RequestAttribute.REQUESTS.getName(), requests);
            request.setAttribute(RequestAttribute.MANAGEMENT_REQUEST.getName(), Command.VIEW_REQUESTS.getName());
        } catch (RequestServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request, e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
        return Page.MANAGEMENT_REQUESTS;
    }

    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }
}
