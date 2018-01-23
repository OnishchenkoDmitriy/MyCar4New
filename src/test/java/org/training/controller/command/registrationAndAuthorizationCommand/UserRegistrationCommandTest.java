package org.training.controller.command.registrationAndAuthorizationCommand;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.jsp.RequestParameters;
import org.training.entity.full.Car;
import org.training.entity.full.User;
import org.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserRegistrationCommandTest {

    private UserRegistrationCommand userRegistrationCommand;
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String password2;

    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
        userRegistrationCommand = new UserRegistrationCommand(userService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        email = "test@test.test";
        firstName = "Ивантест";
        lastName = "Поляковтест";
        phoneNumber = "0664567893";
        password = "12345678";
        password2 = "12345678";
    }

    @Test
    public void execute() throws Exception {

        when(request.getParameter(RequestParameters.EMAIL)).thenReturn(email);
        when(request.getParameter(RequestParameters.FIRST_NAME)).thenReturn(firstName);
        when(request.getParameter(RequestParameters.LAST_NAME)).thenReturn(lastName);
        when(request.getParameter(RequestParameters.PHONE_NUMBER)).thenReturn(phoneNumber);
        when(request.getParameter(RequestParameters.PASSWORD)).thenReturn(password);
        when(request.getParameter(RequestParameters.PASSWORD2)).thenReturn(password2);

        doNothing().when(userService).registerDriver(any(User.class), any(Car.class));

        String page = userRegistrationCommand.execute(request, response);
        verify(request).setAttribute(RequestAttributes.REGISTRATION_IS_SUCCESSFUL, true);
        assertEquals(page, JSPPages.INDEX_PAGE);
    }


}