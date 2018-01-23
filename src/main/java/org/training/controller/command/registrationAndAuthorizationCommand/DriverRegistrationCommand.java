package org.training.controller.command.registrationAndAuthorizationCommand;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.messages.ExceptionMessages;
import org.training.controller.command.Command;
import org.training.controller.util.DataValidation;
import org.training.controller.util.GetEntityFromRequest;
import org.training.entity.full.Car;
import org.training.entity.full.User;
import org.training.exception.RightDataFormat;
import org.training.service.UserService;
import org.training.util.logUtil.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Command for driver registration
 * @see DataValidation
 * @see GetEntityFromRequest
 * @see RightDataFormat
 */
public class DriverRegistrationCommand implements Command {

    UserService userService;

    public DriverRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            return DataValidation.validateDriverData(request);
        } catch (RightDataFormat rightDataFormat) {
            try {
                return DataValidation.carDataValidation(request);
            } catch (RightDataFormat rightDataFormat1) {
                User driver = GetEntityFromRequest.getUserFromRequest(request);
                Car car = GetEntityFromRequest.getCarFromRequest(request);
                try {
                    userService.registerDriver(driver, car);
                } catch (SQLException e) {
                    request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                            rbm.getString(ExceptionMessages.USER_ALREADY_REGISTERED));
                    return JSPPages.DRIVER_REG_PAGE;
                }
                request.setAttribute(RequestAttributes.REGISTRATION_IS_SUCCESSFUL, true);
                logger.info(LogMessageBuilder.INSTANCE.driverRegistrationInfo(driver.getFirstName() + driver.getLastName()));
                return JSPPages.INDEX_PAGE;
            }
        }
    }
}
