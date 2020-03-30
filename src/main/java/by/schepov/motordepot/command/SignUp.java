package by.schepov.motordepot.command;

import by.schepov.motordepot.builder.impl.user.UserBuilder;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.service.UserService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SignUp implements Executable {

    private final UserBuilder userBuilder = new UserBuilder();
    private final UserService userService = UserService.INSTANCE;
    private static final Logger LOGGER = LogManager.getLogger(SignUp.class);

    SignUp(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter(JSPParameter.USERNAME.getValue());
        String password = request.getParameter(JSPParameter.PASSWORD.getValue());
        String email = request.getParameter(JSPParameter.EMAIL.getValue());
        userBuilder.reset();
        User user = userBuilder.withLogin(username)
                .withPassword(password)
                .withEmail(email)
                .withRole(Role.USER)
                .build();
        request.setAttribute(JSPParameter.USERNAME.getValue(), username);
        try {
            userService.insertUser(user);
            request.getSession().setAttribute(SessionAttribute.USER.getName(), user);
        } catch (UserServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.WELCOME;
    }
}
