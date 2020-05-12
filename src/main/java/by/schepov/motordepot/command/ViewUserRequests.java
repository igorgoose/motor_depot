package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.service.user.impl.UserRepositoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewUserRequests implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ViewUserRequests.class);

    private final UserService userService = UserRepositoryService.getInstance();
    private final RequestService requestService = RequestRepositoryService.getInstance();
    private static final String BUNDLE_NAME = "locale";

    ViewUserRequests(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int user_id = Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getName()));
            User foundUser = userService.getUserById(user_id);
            if (foundUser == null) {
                LOGGER.warn("No users have been found by id " + user_id);
                setMessage(request, MessageKey.UNEXPECTED_ERROR);
                return Page.ERROR;
            }
            request.setAttribute(RequestAttribute.USER.getName(), foundUser);
            Set<Request> requests = requestService.getRequestsByUserId(user_id);
            request.setAttribute(RequestAttribute.REQUESTS.getName(), requests);
        } catch (RequestServiceException | UserServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request, e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
        return Page.USER_DETAILS;
    }

    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }
}
