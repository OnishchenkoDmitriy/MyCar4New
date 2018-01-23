package org.training.controller.command.other;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.messages.Messages;
import org.training.exception.NoResultFromDBException;
import org.training.service.UserService;
import org.training.util.properties.BundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AllUsersCommandTest {

    private AllUsersCommand allUsersCommand;
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private List users;

    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
        allUsersCommand = new AllUsersCommand(userService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        users = mock(List.class);
    }

    @Test
    public void execute() throws Exception {
        when(userService.findAllUsers()).thenReturn(users);
        String page = allUsersCommand.execute(request, response);
        verify(request).setAttribute(RequestAttributes.ALL_USERS, users);
        assertEquals(page, JSPPages.ALL_USERS_PAGE);
    }

    @Test
    public void executeWithException() throws Exception {
        when(userService.findAllUsers()).thenThrow(new NoResultFromDBException());
        String page = allUsersCommand.execute(request, response);
        verify(request).setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                BundleManager.INSTANCE.getString(Messages.USER_LIST_IS_EMPTY));
        assertEquals(page, JSPPages.ALL_USERS_PAGE);
    }
}