package by.schepov.motordepot.command;

import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect implements Executable {

    Redirect(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.valueOf(request.getParameter(JSPParameter.ADDRESS.getValue()));
    }
}
