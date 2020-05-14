package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.Action;
import by.schepov.motordepot.parameter.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOut implements Action {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return Page.HOME;
    }
}
