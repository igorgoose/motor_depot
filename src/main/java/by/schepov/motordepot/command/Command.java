package by.schepov.motordepot.command;

import by.schepov.motordepot.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    Page execute(HttpServletRequest request, HttpServletResponse response);
}
