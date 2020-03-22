package by.schepov.motordepot.command;

import by.schepov.motordepot.builder.impl.user.UserBuilder;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements Executable {

    private final UserBuilder userBuilder = new UserBuilder();

    SignUp(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter(JSPParameter.USERNAME.getValue());
        String password = request.getParameter(JSPParameter.PASSWORD.getValue());
        String email = request.getParameter(JSPParameter.EMAIL.getValue());
        userBuilder.reset();
        User user = userBuilder.withLogin(username)
                .withPassword(password)
                .withEmail(email)
                .build();
        request.setAttribute("username", username);
        return Page.WELCOME;
    }
}
