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
import java.util.Iterator;
import java.util.Set;

public class UnblockUser implements Executable {

    private final UserRepositoryService userService = UserRepositoryService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(BlockUser.class);

    UnblockUser(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if (user == null) {
            LOGGER.warn("Null user was provided by session!");
            return Page.HOME;
        }
        try {
            int user_id = Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getName()));
            userService.updateIsBlockedById(user_id, false);
            Set<User> users = userService.getUsersById(user_id);
            Iterator<User> iterator = users.iterator();
            if(iterator.hasNext()){
                request.setAttribute(RequestAttribute.USER.getName(), iterator.next());
            } else {
                LOGGER.warn("No users have been found by id " + user_id);
                return Page.ERROR;
            }
        } catch (UserServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }

        return Page.USER_DETAILS;
    }
}
