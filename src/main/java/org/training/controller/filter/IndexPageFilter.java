package org.training.controller.filter;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * Filter for index page
 */
@WebFilter(urlPatterns = {"/my-car/index_page", "/my-car"})
public class IndexPageFilter extends AbstractFilter {

    /**
     * If user entered the system filter makes redirect to home page depends on user role
     * else filter makes redirect to index page.
     * Also if user entered the system and has current order filter makes redirect to current order page.
     * @param request request
     * @param response response
     * @param filterChain filter chain
     * @param role user role
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void filter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain, String role)
            throws ServletException, IOException {
        if (isNull(role)) {
            request.getRequestDispatcher(JSPPages.INDEX_PAGE).forward(request, response);
        } else {
            if(request.getSession().getAttribute(RequestAttributes.CURRENT_ORDER) != null){
                request.getRequestDispatcher(JSPPages.CURRENT_ORDER_PAGE).forward(request,response);
            }else{
                checkRole(request, response, role);
            }
        }
    }
}
