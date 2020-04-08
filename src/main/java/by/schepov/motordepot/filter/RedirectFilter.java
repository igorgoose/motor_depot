package by.schepov.motordepot.filter;

import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String whereTo = request.getParameter(JSPParameter.ADDRESS.getName());
        if(whereTo != null){
            request.getRequestDispatcher(Page.valueOf(whereTo).getName()).forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

}
