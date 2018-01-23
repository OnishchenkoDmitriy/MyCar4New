package org.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.training.controller.command.registrationAndAuthorizationCommand.LoginCommand;
import org.training.util.properties.BundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    /**
     * Logger for Command classes
     * @see LogManager
     */
    Logger logger = LogManager.getLogger(LoginCommand.class);

    /**
     * @see BundleManager
     */
    BundleManager rbm = BundleManager.INSTANCE;

    /**
     * Do some logic depends on command and returns way to jsp page {@link org.training.constant.jsp.JSPPages}
     * @param request request
     * @param response response
     * @return way to jsp page {@link org.training.constant.jsp.JSPPages}
     * @throws IOException
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
