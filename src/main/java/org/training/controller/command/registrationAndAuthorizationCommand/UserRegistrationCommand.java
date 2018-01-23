package org.training.controller.command.registrationAndAuthorizationCommand;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.messages.ExceptionMessages;
import org.training.controller.command.Command;
import org.training.controller.util.DataValidation;
import org.training.controller.util.GetEntityFromRequest;
import org.training.entity.full.User;
import org.training.exception.RightDataFormat;
import org.training.service.UserService;
import org.training.util.logUtil.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for user registration
 * @see RequestAttributes
 * @see DataValidation
 */
public class UserRegistrationCommand implements Command {

    UserService userService;

    public UserRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        try{
            return DataValidation.validateClientData(request);
        } catch (RightDataFormat rightDataFormat) {
            User user = GetEntityFromRequest.getUserFromRequest(request);
            try {
                userService.registerUser(user);
            } catch (Exception e) {
                request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                        rbm.getString(ExceptionMessages.USER_ALREADY_REGISTERED));
                return JSPPages.USER_REG_PAGE;
            }
            request.setAttribute(RequestAttributes.REGISTRATION_IS_SUCCESSFUL, true);
            logger.info(LogMessageBuilder.INSTANCE.clientRegistrationInfo(user.getFirstName() + user.getLastName()));
            return JSPPages.INDEX_PAGE;
        }
    }
}
