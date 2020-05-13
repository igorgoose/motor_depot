package by.schepov.motordepot.filter;

import by.schepov.motordepot.parameter.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class SessionAttributesFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        String item;
        while (attributeNames.hasMoreElements()) {
            item = attributeNames.nextElement();
            if (item.equals(SessionAttribute.USER.getName()) ||
                    item.equals(SessionAttribute.LOCALE.getName()) ||
                    item.equals(SessionAttribute.ROLE.getName()) ||
                    item.equals(SessionAttribute.USERNAME.getName())) {
                continue;
            }
            session.removeAttribute(item);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
