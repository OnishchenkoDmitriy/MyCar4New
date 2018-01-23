package org.training.controller;

import org.training.controller.command.CommandCreator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see HttpServlet
 */
@WebServlet(urlPatterns = {"/my-car/*"})
public class Controller extends HttpServlet {

    /**
     * @see CommandCreator
     */
    private static CommandCreator commandCreator = CommandCreator.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Gets command name from request uri and do some action depends on command name.
     * Then forward to concrete page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandName = request.getRequestURI().replaceAll(".*/my-car/" , "");
        String page = commandCreator.action(commandName, request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
