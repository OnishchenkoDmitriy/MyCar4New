package org.training.controller.command.other;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.messages.Messages;
import org.training.controller.command.Command;
import org.training.entity.full.User;
import org.training.exception.NoResultFromDBException;
import org.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * Command for getting all users in system
 */
public class AllUsersCommand implements Command {

    UserService userService;

    public AllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param request request
     * @param response response
     * @return way to allUsers page
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<User> users = userService.findAllUsers();
            request.setAttribute(RequestAttributes.ALL_USERS, users);
            return JSPPages.ALL_USERS_PAGE;
        } catch (NoResultFromDBException e) {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                    rbm.getString(Messages.USER_LIST_IS_EMPTY));
            return JSPPages.ALL_USERS_PAGE;
        }
    }
}
