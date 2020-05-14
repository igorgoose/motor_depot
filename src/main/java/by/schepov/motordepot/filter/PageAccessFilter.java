package by.schepov.motordepot.filter;

import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.parameter.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class PageAccessFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(PageAccessFilter.class);
    private static final String BUNDLE_NAME = "locale";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        String uri = request.getRequestURI();
        if(user != null && user.getStatus() == UserStatus.BLOCKED){
            LOGGER.warn("Blocked user tried to access " + uri + "; user: " + user);
            setMessage(request, MessageKey.YOU_ARE_BLOCKED);
            request.getRequestDispatcher(Page.ERROR.getName()).forward(servletRequest, servletResponse);
            return;
        }
        Role role = (Role) request.getSession().getAttribute(SessionAttribute.ROLE.getName());
        String contextPath = request.getContextPath();
        for (Page page : Page.values()) {
            if(uri.equals(contextPath + page.getName()) && !page.getAccessChecker().canBeAccessedBy(role)){
                LOGGER.warn("Page(" + uri + ") was accessed by a user with low priority(" + role + ")!");
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
