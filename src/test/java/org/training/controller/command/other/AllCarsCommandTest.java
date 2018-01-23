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
import static org.mockito.Mockito.*;

public class AllCarsCommandTest {

    private AllCarsCommand allCarsCommand;
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private List cars;

    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
        allCarsCommand = new AllCarsCommand(userService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        cars = mock(List.class);
    }



    @Test
    public void execute() throws Exception {
        when(userService.findAllCars()).thenReturn(cars);
        String page = allCarsCommand.execute(request, response);
        verify(request).setAttribute(RequestAttributes.ALL_CARS, cars);
        assertEquals(page, JSPPages.ALL_CARS_PAGE);
    }

    @Test
    public void executeWithException() throws Exception {
        when(userService.findAllCars()).thenThrow(new NoResultFromDBException());
        String page = allCarsCommand.execute(request, response);
        verify(request).setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                BundleManager.INSTANCE.getString(Messages.CAR_LIST_IS_EMPTY));;
        assertEquals(page, JSPPages.ALL_CARS_PAGE);
    }

}