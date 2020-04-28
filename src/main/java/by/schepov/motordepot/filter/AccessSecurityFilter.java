package by.schepov.motordepot.filter;

import by.schepov.motordepot.command.Command;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessSecurityFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AccessSecurityFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String commandName = request.getParameter(JSPParameter.COMMAND.getName());
        if(commandName != null){
            Command command = Command.getCommandByName(commandName);
            Role role = (Role) request.getSession().getAttribute(SessionAttribute.ROLE.getName());
            if(!command.getAccessChecker().canBeAccessedBy(role)){
                LOGGER.error("Command(" + command.getName() + ") was accessed by a user with low priority(" + role + ")!");
                request.getRequestDispatcher(Page.ERROR.getName()).forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
