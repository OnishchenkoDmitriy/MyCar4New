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
import java.sql.SQLException;

/**
 * Command for canceling current order
 * @see OtherUtil
 */
public class CancelOrderCommand implements Command{

    OrderService orderService;

    public CancelOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @param request request
     * @param response response
     * @return way to home page depends on user role
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order order = (Order) request.getSession().getAttribute(RequestAttributes.CURRENT_ORDER);
        if(order != null){
            try {
                orderService.cancelOrder(order);
            } catch (SQLException e) {
                request.getSession().setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                        rbm.getString(ExceptionMessages.CANCEL_ORDER_ERROR));
                return JSPPages.ERROR_PAGE;
            }
            logger.info(LogMessageBuilder.INSTANCE.cancelOrderInfo((String) request.getAttribute(RequestAttributes.USER_NAME)));
            request.getSession().removeAttribute(RequestAttributes.CURRENT_ORDER);
        }
        return OtherUtil.getUserIndexPage(request);
    }
}
