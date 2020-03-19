package by.schepov.motordepot.filter;


import by.schepov.motordepot.page.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebFilter(urlPatterns = "/*")
public class LanguageFilter implements Filter {


    private static final String RUSSIAN = "ru";
    private static final String LOCALE_BUNDLE_PATH = "locale";
    //private static final String ENGLISH = "en";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String localeName = request.getParameter("language");
        if (localeName == null) {
            localeName = (String) session.getAttribute("locale");
            if (localeName == null) {
                localeName = RUSSIAN;
            }
        }
        session.setAttribute("locale", localeName);
        //Locale locale = new Locale(localeName);
        //session.setAttribute("locale", ResourceBundle.getBundle(LOCALE_BUNDLE_PATH, locale));
        //response.setLocale(locale);
//        request.setAttribute("locale", ResourceBundle.getBundle(LOCALE_BUNDLE_PATH, locale));
        //request.getRequestDispatcher(Page.HOME.getName()).forward(request, response);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
