package org.training.controller.command.redirectCommand;

import org.training.constant.jsp.JSPPages;
import org.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for redirect to driver registration page
 */
public class DriverRegPageCommand implements Command {
    /**
     *
     * @param request request
     * @param response response
     * @return way to driver registration page
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return JSPPages.DRIVER_REG_PAGE;
    }
}
