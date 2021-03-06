package org.training.controller.filter;

import org.training.constant.jsp.JSPPages;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * filter for driver registration page
 */
@WebFilter(urlPatterns = {"/my-car/driver_registration_page"})
public class DriverRegistrationPageFilter extends AbstractFilter {
    /**
     * If user entered the system he can not go to driver registration page
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
            request.getRequestDispatcher(JSPPages.DRIVER_REG_PAGE).forward(request, response);
        } else {
            checkRole(request, response, role);
        }
    }


}
