package org.training.controller.command.redirectCommand;

import org.junit.Before;
import org.junit.Test;
import org.training.constant.jsp.JSPPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class IndexPageCommandTest {

    private IndexPageCommand indexPageCommand;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        indexPageCommand = new IndexPageCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void execute() throws Exception {
        String page = indexPageCommand.execute(request, response);
        assertEquals(page, JSPPages.INDEX_PAGE);
    }

}