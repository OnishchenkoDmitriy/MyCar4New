package org.training.controller.command.registrationAndAuthorizationCommand;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.jsp.JSPPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogoutCommandTest {

    private LogoutCommand logoutCommand;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        logoutCommand = new LogoutCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void execute() throws Exception {
        when(request.getSession()).thenReturn(session);
        String page = logoutCommand.execute(request, response);
        verify(session).invalidate();
        assertNotNull(page);
        assertEquals(page, JSPPages.INDEX_PAGE);
    }

}