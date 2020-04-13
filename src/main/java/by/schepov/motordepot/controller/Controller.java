package by.schepov.motordepot.controller;


import by.schepov.motordepot.command.Command;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = {"/controller", "/jsp/controller"})
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    public void init() throws ServletException {
        try {
            ConnectionPool.INSTANCE.initializePool();
        } catch (ConnectionPoolException e) {
            LOGGER.fatal(e);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.INSTANCE.closePool();
        } catch (ConnectionPoolException e) {
            LOGGER.warn(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(JSPParameter.COMMAND.getName());
        Command command = Command.getCommandByName(commandName);
        Page page = command.execute(req, resp);
        req.getSession().setAttribute(SessionAttribute.LAST_PAGE.getName(), page);
        req.getRequestDispatcher(page.getName()).forward(req, resp);
    }
}
