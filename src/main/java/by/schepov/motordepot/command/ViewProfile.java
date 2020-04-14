package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewProfile implements Executable {

    private final RequestRepositoryService requestService = RequestRepositoryService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(ViewProfile.class);

    ViewProfile(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if(user == null){
            LOGGER.error("Null user was provided by session!");
            return Page.ERROR;
        }
        try {
            Set<Request> requests = requestService.findRequestsByUserId(user.getId());
            request.setAttribute(RequestAttribute.USERNAME.getName(), user.getUsername());
            request.setAttribute(RequestAttribute.ROLE.getName(), user.getRole().getId());
            request.setAttribute(RequestAttribute.REQUESTS.getName(), requests);
        } catch (RequestServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.PROFILE;
    }
}
