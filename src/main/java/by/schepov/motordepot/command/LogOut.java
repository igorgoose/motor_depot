package by.schepov.motordepot.command;

import by.schepov.motordepot.parameter.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOut implements Executable {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return Page.HOME;
    }
}
