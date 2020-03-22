package by.schepov.motordepot.command;

import by.schepov.motordepot.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements Command {

    SignUp(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
