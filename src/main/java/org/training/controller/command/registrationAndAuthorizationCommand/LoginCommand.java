package org.training.controller.command.registrationAndAuthorizationCommand;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.jsp.RequestParameters;
import org.training.constant.messages.Messages;
import org.training.controller.command.Command;
import org.training.controller.util.OtherUtil;
import org.training.entity.full.User;
import org.training.exception.NoResultFromDBException;
import org.training.service.UserService;
import org.training.util.logUtil.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for log in the system
 * @see RequestParameters
 * @see RequestAttributes
 * @see Messages
 */
public class LoginCommand implements Command {

    UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Checks if current user is exist.
     * If user is exist checks user password then set attributes to current session,
     * and returns way to index page.
     * If user do not found sent error message.
     * If password do not correct sent error message.
     * @param request request
     * @param response response
     * @return way to jsp page
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        try {
            user = userService.findByEmail(request.getParameter(RequestParameters.EMAIL));
        } catch (NoResultFromDBException e) {
            request.setAttribute(RequestAttributes.ERROR_MESSAGE, rbm.getString(Messages.NO_SUCH_USER_FOUND));
            return JSPPages.INDEX_PAGE;
        }
        if (user.getPassword().equals(request.getParameter(RequestParameters.PASSWORD))) {

            String userRole = user.getRole();
            request.getSession().setAttribute(RequestAttributes.USER, user);
            request.getSession().setAttribute(RequestAttributes.ROLE, userRole);
            request.getSession().setAttribute(RequestAttributes.USER_NAME, user.getFirstName() + " " + user.getLastName());
            request.getSession().setAttribute(RequestAttributes.LOG_OUT, true);
            request.getSession().setAttribute(RequestAttributes.NO_LANGUAGE, true);

            logger.info(LogMessageBuilder.INSTANCE.logInInfo(user));
            return OtherUtil.getUserIndexPage(request);
        } else {
            request.setAttribute(RequestAttributes.ERROR_MESSAGE,
                    rbm.getString(Messages.WRONG_PASSWORD));
            return JSPPages.INDEX_PAGE;
        }
    }
}
