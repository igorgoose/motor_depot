package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.service.user.impl.UserRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewUsers implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ViewUsers.class);

    //todo create ServiceFactory
    private final UserService userService = UserRepositoryService.getInstance();

    ViewUsers(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if (user == null) {
            LOGGER.error("Null user was provided by session!");
            return Page.ERROR;
        }
        try {
            Set<User> users = userService.getAllUsers();
            request.setAttribute(RequestAttribute.USERNAME.getName(), user.getUsername());
            request.setAttribute(RequestAttribute.ROLE.getName(), user.getRole().getId());
            request.setAttribute(RequestAttribute.USERS.getName(), users);
            request.setAttribute(RequestAttribute.MANAGEMENT_REQUEST.getName(), Command.VIEW_USERS.getName());
        } catch (UserServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.MANAGEMENT_USERS;
    }

}
