package by.schepov.motordepot.filter;

import by.schepov.motordepot.command.impl.Command;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.parameter.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AccessSecurityFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AccessSecurityFilter.class);
    private static final String BUNDLE_NAME = "locale";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String commandName = request.getParameter(JSPParameter.COMMAND.getName());
        Role role = (Role) request.getSession().getAttribute(SessionAttribute.ROLE.getName());
        if(commandName != null){
            Command command = Command.getCommandByName(commandName);
            if(role != Role.GUEST){
                User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
                if(user.getStatus() == UserStatus.BLOCKED){
                    LOGGER.info("Command(" + command.getName() + ") was accessed by a blocked user!");
                    setMessage(request, MessageKey.YOU_ARE_BLOCKED);
                    request.getSession().invalidate();
                    request.getRequestDispatcher(Page.ERROR.getName()).forward(servletRequest, servletResponse);
                    return;
                }
            }
            if(!command.getAccessChecker().canBeAccessedBy(role)){
                LOGGER.error("Command(" + command.getName() + ") was accessed by a user with low priority(" + role + ")!");
                setMessage(request, MessageKey.ACCESS_DENIED);
                request.getRequestDispatcher(Page.ERROR.getName()).forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        Locale locale = new Locale((String)request.getSession().getAttribute(SessionAttribute.LOCALE.getName()));
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        request.setAttribute(RequestAttribute.MESSAGE.getName(), bundle.getString(messageKey.getValue()));
    }
}
