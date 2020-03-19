package by.schepov.motordepot.filter;


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
        String localeName = request.getParameter("language");
        if (localeName == null) {
            localeName = (String) session.getAttribute("locale");
            if (localeName == null) {
                localeName = RUSSIAN;
            }
        }
        session.setAttribute("locale", localeName);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
