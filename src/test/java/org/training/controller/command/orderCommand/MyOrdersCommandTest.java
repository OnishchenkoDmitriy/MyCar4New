package org.training.controller.command.orderCommand;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.messages.Messages;
import org.training.entity.full.User;
import org.training.exception.NoResultFromDBException;
import org.training.service.OrderService;
import org.training.util.properties.BundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MyOrdersCommandTest {

    private MyOrdersCommand myOrdersCommand;
    private OrderService orderService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private String role;
    private User user;
    List orders;

    @Before
    public void setUp() throws Exception {
        orderService = mock(OrderService.class);
        myOrdersCommand = new MyOrdersCommand(orderService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        user = mock(User.class);
        orders = mock(List.class);
        role = User.Roles.ADMIN.toString();
    }

    @Test
    public void execute() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(RequestAttributes.USER)).thenReturn(user);
        when(orderService.getAllOrders(any(User.class))).thenReturn(orders);
        String page = myOrdersCommand.execute(request, response);
        verify(request).setAttribute(RequestAttributes.MY_ORDERS, orders);
        assertEquals(page, JSPPages.MY_ORDERS_PAGE);
    }

    @Test
    public void executeWithException() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(RequestAttributes.USER)).thenReturn(user);
        when(orderService.getAllOrders(any(User.class))).thenThrow(new NoResultFromDBException());
        String page = myOrdersCommand.execute(request, response);
        verify(request).setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                BundleManager.INSTANCE.getString(Messages.YOU_DO_NOT_HAVE_ANY_ORDERS));
        assertEquals(page, JSPPages.MY_ORDERS_PAGE);
    }

    @Test
    public void executeWithUserIsNull() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(RequestAttributes.USER)).thenReturn(null);
        String page = myOrdersCommand.execute(request, response);
        verify(request).setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                BundleManager.INSTANCE.getString(Messages.LOG_IN_PLEASE));
        assertEquals(page, JSPPages.INDEX_PAGE);
    }


}