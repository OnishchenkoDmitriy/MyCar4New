package org.training.controller.command.other;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.jsp.RequestParameters;
import org.training.constant.messages.ExceptionMessages;
import org.training.controller.command.Command;
import org.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserCommand implements Command {

    UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentUserId = request.getParameter(RequestParameters.CURRENT_USER_ID);
        try {
            userService.deleteUserById(Integer.valueOf(currentUserId));
        } catch (SQLException e) {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                    rbm.getString(ExceptionMessages.USER_WAS_NOT_DELETED));
            return JSPPages.ERROR_PAGE;
        }
        response.sendRedirect(JSPPages.ALL_USERS_PAGE);
        return null;
    }
}
