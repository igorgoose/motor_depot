package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.user.impl.UserRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUser implements Executable {

    private final UserRepositoryService userService = UserRepositoryService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(BlockUser.class);

    BlockUser() {

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int user_id = Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getName()));
            userService.updateIsBlockedById(user_id, true);
            User foundUser = userService.getUserById(user_id);
            if (foundUser == null) {
                LOGGER.warn("No users have been found by id " + user_id);
                return Page.ERROR;
            }
            request.setAttribute(RequestAttribute.USER.getName(), foundUser);
        } catch (UserServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.USER_DETAILS;
    }
}
