package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewRequests implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ViewRequests.class);

    private final RequestRepositoryService requestService = RequestRepositoryService.getInstance();

    ViewRequests(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if (user == null) {
            LOGGER.warn("Null user was provided by session!");
            return Page.HOME;
        }
        try {
            Set<Request> requests = requestService.getAllRequests();
            request.setAttribute(RequestAttribute.REQUESTS.getName(), requests);
            request.setAttribute(RequestAttribute.MANAGEMENT_REQUEST.getName(), Command.VIEW_REQUESTS.getName());
        } catch (RequestServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.MANAGEMENT_REQUESTS;
    }
}
