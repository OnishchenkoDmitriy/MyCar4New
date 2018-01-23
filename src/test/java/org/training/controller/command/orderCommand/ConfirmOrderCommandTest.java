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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ConfirmOrderCommandTest {

    private ConfirmOrderCommand confirmOrderCommand;
    private OrderService orderService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private String role;
    private Order currentOrder;

    @Before
    public void setUp() throws Exception {
        orderService = mock(OrderService.class);
        confirmOrderCommand = new ConfirmOrderCommand(orderService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        role = User.Roles.ADMIN.toString();
        currentOrder = mock(Order.class);
    }

    @Test
    public void execute() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(RequestAttributes.ROLE)).thenReturn(role);
        when(session.getAttribute(RequestAttributes.CURRENT_ORDER)).thenReturn(currentOrder);
        doNothing().when(orderService).confirmOrder(any(Order.class));
        String page = confirmOrderCommand.execute(request, response);
        verify(orderService).confirmOrder(any(Order.class));
        verify(session).removeAttribute(RequestAttributes.CURRENT_ORDER);
        assertEquals(page, JSPPages.ADMIN_PAGE);
    }

}