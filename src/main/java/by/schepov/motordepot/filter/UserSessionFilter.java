package by.schepov.motordepot.filter;

import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.parameter.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class UserSessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        if(session == null){
            session = request.getSession();
            session.setAttribute(SessionAttribute.ROLE.getName(), Role.GUEST);
            request.getRequestDispatcher(Page.HOME.getName()).forward(request, resp);
            return;
        }
        Role role = (Role) session.getAttribute(SessionAttribute.ROLE.getName());
        User user = (User) session.getAttribute(SessionAttribute.USER.getName());
        if(role == null){
            session.setAttribute(SessionAttribute.ROLE.getName(), Role.GUEST);
            request.getRequestDispatcher(Page.HOME.getName());
        }
        if(user != null){
            session.setAttribute(RequestAttribute.USERNAME.getName(), user.getUsername());
        }
        chain.doFilter(req, resp);
    }
}
