package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.builder.impl.user.UserBuilder;
import by.schepov.motordepot.command.RepositoryAction;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.parameter.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogIn extends RepositoryAction {

    private final UserBuilder userBuilder = new UserBuilder();
    private final UserService userService = serviceFactory.createUserService();
    private static final Logger LOGGER = LogManager.getLogger(LogIn.class);
    private static final String BUNDLE_NAME = "locale";


    LogIn(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter(JSPParameter.USERNAME.getName());
        String password = request.getParameter(JSPParameter.PASSWORD.getName());
        userBuilder.reset();
        User user = userBuilder.withLogin(username)
                .withPassword(password)
                .withRole(Role.USER)
                .build();
        try {
            userService.authorizeUser(user);
            request.getSession().setAttribute(SessionAttribute.USER.getName(), user);
            request.getSession().setAttribute(SessionAttribute.ROLE.getName(), user.getRole());
        } catch (UserServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request.getSession(), e.getMessageBundleKey());
            }
            return Page.AUTHORIZE;
        }
        return Page.HOME;
    }

    private void setMessage(HttpSession session, MessageKey messageKey){
        setMessage(session, messageKey, BUNDLE_NAME);
    }
}
