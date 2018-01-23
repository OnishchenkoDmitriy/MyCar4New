package org.training.controller.command.redirectCommand;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.jsp.JSPPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ContactPageCommandTest {

    private ContactPageCommand contactPageCommand;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        contactPageCommand = new ContactPageCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void execute() throws Exception {
        String page = contactPageCommand.execute(request, response);
        assertEquals(page, JSPPages.CONTACT_PAGE);
    }

}