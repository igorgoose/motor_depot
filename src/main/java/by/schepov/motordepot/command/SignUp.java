package by.schepov.motordepot.command;

import by.schepov.motordepot.builder.impl.user.UserBuilder;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.service.user.impl.UserRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;


public class SignUp implements Executable {

    private final UserBuilder userBuilder = new UserBuilder();
    private final UserRepositoryService userService = UserRepositoryService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(SignUp.class);
    private static final String BUNDLE_NAME = "locale";

    SignUp(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter(JSPParameter.USERNAME.getName());
        String password = request.getParameter(JSPParameter.PASSWORD.getName());
        String email = request.getParameter(JSPParameter.EMAIL.getName());
        String repeatedPassword = request.getParameter(JSPParameter.REPEAT_PASSWORD.getName());
        userBuilder.reset();
        User user = userBuilder.withLogin(username)
                .withPassword(password)
                .withEmail(email)
                .withRole(Role.USER)
                .build();
        request.setAttribute(JSPParameter.USERNAME.getName(), username);
        try {
            userService.signUpUser(user, repeatedPassword);
            request.getSession().setAttribute(SessionAttribute.USER.getName(), user);
        } catch (UserServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request, e.getMessageBundleKey());
            }
            return Page.SIGN_UP;
        }
        return Page.AUTHORIZE;
    }

    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }
}
