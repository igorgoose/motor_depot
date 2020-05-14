package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.RepositoryAction;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.parameter.SessionAttribute;
import by.schepov.motordepot.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewProfile extends RepositoryAction {

    private final UserService userService = serviceFactory.createUserService();
    private static final Logger LOGGER = LogManager.getLogger(ViewProfile.class);
    private static final String BUNDLE_NAME = "locale";

    ViewProfile(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        try {
            int id = user.getId();
            User foundUser = userService.getUserById(id);
            if(foundUser == null){
                LOGGER.warn("User not found " + user);
                setMessage(request, MessageKey.UNEXPECTED_ERROR);
                return Page.ERROR;
            }
            request.getSession().setAttribute(SessionAttribute.USER.getName(), foundUser);
            request.getSession().setAttribute(SessionAttribute.ROLE.getName(), foundUser.getRole());
            request.getSession().setAttribute(RequestAttribute.USERNAME.getName(), foundUser.getUsername());
        } catch (UserServiceException e) {
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
