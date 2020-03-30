package by.schepov.motordepot.filter;


import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.session.SessionAttribute;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LocaleFilter implements Filter {

    private static final String RUSSIAN = "ru";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        Page currentPage = (Page) session.getAttribute(SessionAttribute.CURRENT_PAGE.getName());
        if(currentPage == null)
        {
            currentPage = Page.HOME;
            session.setAttribute(SessionAttribute.CURRENT_PAGE.getName(), currentPage);
            session.setAttribute(SessionAttribute.LOCALE.getName(), RUSSIAN);
            request.getRequestDispatcher(currentPage.getName()).forward(request, response);
            return;
        }
        String localeName = request.getParameter(JSPParameter.LANGUAGE.getValue());
        if (localeName == null) {
            localeName = (String) session.getAttribute(SessionAttribute.LOCALE.getName());
            if (localeName == null) {
                localeName = RUSSIAN;
            }
            session.setAttribute(SessionAttribute.LOCALE.getName(), localeName);
            chain.doFilter(request, response);
        } else {
            session.setAttribute(SessionAttribute.LOCALE.getName(), localeName);
            request.getRequestDispatcher(currentPage.getName()).forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
