package org.training.controller.command.orderCommand;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.messages.ExceptionMessages;
import org.training.controller.command.Command;
import org.training.controller.util.OtherUtil;
import org.training.entity.full.Order;
import org.training.service.OrderService;
import org.training.util.logUtil.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for confirming current order
 */
public class ConfirmOrderCommand implements Command {

    OrderService orderService;

    public ConfirmOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *
     * @param request request
     * @param response response
     * @return way to home page depends on user role
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order currentOrder = (Order) request.getSession().getAttribute(RequestAttributes.CURRENT_ORDER);
        try {
            orderService.confirmOrder(currentOrder);
        } catch (Exception e) {
            request.getSession().setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                    rbm.getString(ExceptionMessages.CONFIRM_ORDER_ERROR));
            return JSPPages.ERROR_PAGE;
        }
        request.getSession().removeAttribute(RequestAttributes.CURRENT_ORDER);
        logger.info(LogMessageBuilder.INSTANCE.confirmOrderInfo((String) request.getSession().getAttribute(RequestAttributes.USER_NAME)));
        return OtherUtil.getUserIndexPage(request);
    }
}
