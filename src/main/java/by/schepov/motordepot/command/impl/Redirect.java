package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.Action;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect implements Action {

    Redirect(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.valueOf(request.getParameter(JSPParameter.ADDRESS.getName()));
    }

}
