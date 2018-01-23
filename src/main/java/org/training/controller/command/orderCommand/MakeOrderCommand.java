package org.training.controller.command.orderCommand;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.messages.ExceptionMessages;
import org.training.controller.command.Command;
import org.training.controller.util.DataValidation;
import org.training.controller.util.GetEntityFromRequest;
import org.training.controller.util.OtherUtil;
import org.training.entity.full.Address;
import org.training.entity.full.Order;
import org.training.entity.full.User;
import org.training.exception.NoFreeCarSuchTypeException;
import org.training.exception.RightDataFormat;
import org.training.service.OrderService;
import org.training.util.logUtil.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for making order
 */
public class MakeOrderCommand implements Command {

    OrderService orderService;

    public MakeOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *
     * @param request request
     * @param response response
     * @return way to jsp page
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute(RequestAttributes.CURRENT_ORDER) == null) {
            Address departureAddress = GetEntityFromRequest.getDepartureAddressFromRequest(request);
            Address arrivalAddress = GetEntityFromRequest.getArrivalAddressFromRequest(request);
            String carType = request.getParameter(RequestAttributes.CAR_TYPE);

            try {
                return DataValidation.addressDataValidation(request);
            } catch (RightDataFormat e1) {
                User client = (User) request.getSession().getAttribute(RequestAttributes.USER);
                Order order = null;
                try {
                    order = orderService.makeOrder(client, departureAddress, arrivalAddress, carType);
                    logger.info(LogMessageBuilder.INSTANCE.makeOrderInfo(client.getFirstName() + client.getLastName()));
                    request.getSession().setAttribute(RequestAttributes.CURRENT_ORDER, order);
                    return JSPPages.CURRENT_ORDER_PAGE;
                } catch (NoFreeCarSuchTypeException e) {
                    logger.error(e.getMessage());
                    request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, e.getMessage());
                    return OtherUtil.getUserIndexPage(request);
                } catch (Exception e2) {
                    request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                            rbm.getString(ExceptionMessages.MAKE_ORDER_ERROR));
                    return OtherUtil.getUserIndexPage(request);
                }
            }
        } else {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                    rbm.getString(ExceptionMessages.MAKE_ORDER_ERROR));
            return OtherUtil.getUserIndexPage(request);
        }
    }


}
