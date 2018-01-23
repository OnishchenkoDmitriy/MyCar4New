package org.training.controller.command.other;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestParameters;
import org.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class DeleteUserCommandTest {

    private DeleteUserCommand deleteUserCommand;
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
        deleteUserCommand = new DeleteUserCommand(userService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void execute() throws Exception {
        String userID = "111";
        when(request.getParameter(RequestParameters.CURRENT_USER_ID)).thenReturn(userID);
        doNothing().when(userService).deleteUserById(anyInt());
        deleteUserCommand.execute(request, response);
        verify(response).sendRedirect(JSPPages.ALL_USERS_PAGE);
    }

}