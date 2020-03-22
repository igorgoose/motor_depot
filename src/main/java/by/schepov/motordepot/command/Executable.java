package by.schepov.motordepot.command;

import by.schepov.motordepot.jsp.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Executable {
    Page execute(HttpServletRequest request, HttpServletResponse response);
}
