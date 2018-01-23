package org.training.controller.command.registrationAndAuthorizationCommand;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.controller.command.Command;
import org.training.util.logUtil.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for log out the system
 */
public class LogoutCommand implements Command {

    /**
     * Invalidate current session and returns way to the index page
     * @param request request
     * @param response response
     * @return
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info(LogMessageBuilder.INSTANCE.logOutInfo((String) request.getSession()
                .getAttribute(RequestAttributes.USER_NAME)));
        request.getSession().invalidate();
        return JSPPages.INDEX_PAGE;
    }
}
