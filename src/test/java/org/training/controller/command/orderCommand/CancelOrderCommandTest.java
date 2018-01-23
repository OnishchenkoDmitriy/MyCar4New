package org.training.controller.command.orderCommand;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.entity.full.Order;
import org.training.entity.full.User;
import org.training.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CancelOrderCommandTest {

    private CancelOrderCommand cancelOrderCommand;
    private OrderService orderService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private Order order;
    private String role;

    @Before
    public void setUp() throws Exception {
        orderService = mock(OrderService.class);
        cancelOrderCommand = new CancelOrderCommand(orderService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        order = mock(Order.class);
        role = User.Roles.ADMIN.toString();
    }


    @Test
    public void execute() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(RequestAttributes.CURRENT_ORDER)).thenReturn(order);
        when(session.getAttribute(RequestAttributes.ROLE)).thenReturn(role);
        doNothing().when(orderService).cancelOrder(any(Order.class));
        String page = cancelOrderCommand.execute(request, response);
        verify(orderService).cancelOrder(order);
        verify(session).removeAttribute(RequestAttributes.CURRENT_ORDER);
        assertTrue(page.equals(JSPPages.ADMIN_PAGE));
    }

}