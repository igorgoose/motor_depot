package by.schepov.motordepot.command;

import by.schepov.motordepot.jsp.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn implements Executable {

    LogIn(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.WELCOME;
    }
}
