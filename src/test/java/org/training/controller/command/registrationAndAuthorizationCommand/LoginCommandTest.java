package org.training.controller.command.registrationAndAuthorizationCommand;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.global.Roles;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.jsp.RequestParameters;
import org.training.entity.full.User;
import org.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginCommandTest {

    private LoginCommand loginCommand;
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private User user;
    private String email;
    private String password;
    private Integer id;
    private String role;


    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
        loginCommand = new LoginCommand(userService);
        user = mock(User.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        email = "faoksss@outlook.com";
        password = "111";
        id = 1;
        role = Roles.CLIENT;

    }

    @Test
    public void execute() throws Exception {

        when(user.getId()).thenReturn(id);
        when(user.getEmail()).thenReturn(email);
        when(user.getPassword()).thenReturn(password);
        when(user.getRole()).thenReturn(role);

        when(session.getAttribute(RequestAttributes.ROLE)).thenReturn(role);

        when(request.getParameter(RequestParameters.EMAIL)).thenReturn(email);
        when(request.getParameter(RequestParameters.PASSWORD)).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        when(userService.findByEmail(anyString())).thenReturn(user);

        String page = loginCommand.execute(request, response);
        assertNotNull(page);
        assertEquals(page, JSPPages.CLIENT_PAGE);
    }

}