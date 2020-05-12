package by.schepov.motordepot.filter;


import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.session.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LocaleFilter implements Filter {

    private static final String RUSSIAN = "ru";


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        req.getServletContext();
        String localeName = request.getParameter(JSPParameter.LANGUAGE.getName());
        if (localeName == null) {
            localeName = (String) session.getAttribute(SessionAttribute.LOCALE.getName());
            if (localeName == null) {
                localeName = RUSSIAN;
            }
            session.setAttribute(SessionAttribute.LOCALE.getName(), localeName);
            chain.doFilter(request, response);
            return;
        } else {
            session.setAttribute(SessionAttribute.LOCALE.getName(), localeName);
        }
        request.getRequestDispatcher(Page.HOME.getName()).forward(request, response);
    }

}
