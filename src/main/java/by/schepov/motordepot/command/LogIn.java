package by.schepov.motordepot.command;

import by.schepov.motordepot.builder.impl.user.UserBuilder;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.impl.RequestService;
import by.schepov.motordepot.service.impl.UserService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn implements Executable {

    private final UserBuilder userBuilder = new UserBuilder();
    private final UserService userService = UserService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(SignUp.class);

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
            return Page.ERROR;
        }
        return Page.HOME;
    }
}
