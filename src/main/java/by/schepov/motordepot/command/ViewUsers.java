package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.service.user.impl.UserRepositoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewUsers implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ViewUsers.class);
    private static final String BUNDLE_NAME = "locale";
    //todo create ServiceFactory
    private final UserService userService = UserRepositoryService.getInstance();

    ViewUsers(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Set<User> users = userService.getAllUsers();
            request.setAttribute(RequestAttribute.USERS.getName(), users);
        } catch (UserServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request, e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
        return Page.MANAGEMENT_USERS;
    }

    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }

}
