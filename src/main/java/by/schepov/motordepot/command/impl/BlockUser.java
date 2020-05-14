package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.RepositoryAction;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.parameter.*;
import by.schepov.motordepot.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BlockUser extends RepositoryAction {

    private final UserService userService = serviceFactory.createUserService();
    private static final Logger LOGGER = LogManager.getLogger(BlockUser.class);
    private static final String BUNDLE_NAME = "locale";

    BlockUser() {

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int userId = Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getName()));
            userService.updateStatusById(userId, UserStatus.BLOCKED);
            User foundUser = userService.getUserById(userId);
            if (foundUser == null) {
                LOGGER.warn("No users have been found by id " + userId);
                setMessage(request.getSession(), MessageKey.UNEXPECTED_ERROR);
                return Page.ERROR;
            }
            request.getSession().setAttribute(SessionAttribute.THAT_USER.getName(), foundUser);
        } catch (UserServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request.getSession(), e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
        return Page.USER_DETAILS;
    }

    private void setMessage(HttpSession session, MessageKey messageKey){
        setMessage(session, messageKey, BUNDLE_NAME);
    }
}
