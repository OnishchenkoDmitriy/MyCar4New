package org.training.controller.command.orderCommand;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.entity.full.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ConfirmOrderCommandTest {

    private ConfirmOrderCommand confirmOrderCommand;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private String role;

    @Before
    public void setUp() throws Exception {
        confirmOrderCommand = new ConfirmOrderCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        role = User.Roles.ADMIN.toString();
    }

    @Test
    public void execute() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(RequestAttributes.ROLE)).thenReturn(role);
        String page = confirmOrderCommand.execute(request, response);
        verify(session).removeAttribute(RequestAttributes.CURRENT_ORDER);
        assertEquals(page, JSPPages.ADMIN_PAGE);
    }

}