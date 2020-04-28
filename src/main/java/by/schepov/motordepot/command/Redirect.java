package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect implements Executable{

    private static final Logger LOGGER = LogManager.getLogger(Redirect.class);

    Redirect(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.valueOf(request.getParameter(JSPParameter.ADDRESS.getName()));
    }

}
