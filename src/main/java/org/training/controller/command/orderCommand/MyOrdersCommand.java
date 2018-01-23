package org.training.controller.command.orderCommand;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.messages.Messages;
import org.training.controller.command.Command;
import org.training.entity.full.Order;
import org.training.entity.full.User;
import org.training.exception.NoResultFromDBException;
import org.training.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Command for getting all orders for current user
 */
public class MyOrdersCommand implements Command {

    OrderService orderService;

    public MyOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *
     * @param request request
     * @param response response
     * @return way to all orders page
     * if orders list is empty
     * @return way to jsp page
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute(RequestAttributes.USER);
        if (user != null) {
            try {
                List<Order> orders = orderService.getAllOrders(user);
                request.setAttribute(RequestAttributes.MY_ORDERS, orders);
                return JSPPages.MY_ORDERS_PAGE;
            } catch (NoResultFromDBException e) {
                request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                        rbm.getString(Messages.YOU_DO_NOT_HAVE_ANY_ORDERS));
                return JSPPages.MY_ORDERS_PAGE;
            }
        } else {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                    rbm.getString(Messages.LOG_IN_PLEASE));
            return JSPPages.INDEX_PAGE;
        }

    }
}
