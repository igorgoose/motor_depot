package by.schepov.motordepot.filter;

import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.session.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class UserSessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER.getName());
        Role role;
        if(user == null){
            role = Role.GUEST;
            session.setAttribute(SessionAttribute.ROLE.getName(), Role.GUEST);
        } else {
            role = user.getRole();
            request.setAttribute(RequestAttribute.USERNAME.getName(), user.getUsername());
        }
        request.setAttribute(RequestAttribute.ROLE.getName(), role.getId());
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
