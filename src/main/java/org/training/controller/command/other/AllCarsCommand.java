package org.training.controller.command.other;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.messages.Messages;
import org.training.controller.command.Command;
import org.training.entity.full.Car;
import org.training.exception.NoResultFromDBException;
import org.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Command for getting all cars in system
 */
public class AllCarsCommand implements Command {

    UserService userService;

    public AllCarsCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param request request
     * @param response response
     * @return way to allCars page
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Car> cars = userService.findAllCars();
            request.setAttribute(RequestAttributes.ALL_CARS, cars);
            return JSPPages.ALL_CARS_PAGE;
        } catch (NoResultFromDBException e) {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                    rbm.getString(Messages.CAR_LIST_IS_EMPTY));
            return JSPPages.ALL_CARS_PAGE;
        }
    }
}
