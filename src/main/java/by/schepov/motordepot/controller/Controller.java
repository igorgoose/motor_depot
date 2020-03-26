package by.schepov.motordepot.controller;


import by.schepov.motordepot.command.Command;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller", "/jsp/controller"})
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    private Page currentPage = Page.HOME;

    @Override
    public void init() throws ServletException {
        try {
            ConnectionPool.INSTANCE.initializePool();
        } catch (ConnectionPoolException e) {
            //todo log
            LOGGER.fatal(e);
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
        String languageChosen = req.getParameter(JSPParameter.LANGUAGE.getValue());
        Page page;
        if (languageChosen != null) {
            page = currentPage;
        } else {
            String commandName = req.getParameter(JSPParameter.COMMAND.getValue());
            Command command = Command.getCommandByName(commandName);
            page = command.execute(req, resp);
            currentPage = page;
        }
        req.getRequestDispatcher(page.getName()).forward(req, resp);
    }
}
