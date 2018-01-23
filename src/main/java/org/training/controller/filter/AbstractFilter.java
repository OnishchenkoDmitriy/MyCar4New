package org.training.controller.filter;

import org.training.constant.global.Roles;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Abstract class for all filters
 * @see Filter
 * @see FilterChain
 */
public abstract class AbstractFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession();
        final String role = (String) session.getAttribute(RequestAttributes.ROLE);
        filter(request, response, filterChain, role);
    }

    @Override
    public void destroy() {

    }

    /**
     * Check session attribute - role and redirect ro home page depends on user role
     * @param request
     * @param response
     * @param role
     * @throws ServletException
     * @throws IOException
     */
    void checkRole(HttpServletRequest request, HttpServletResponse response, String role) throws ServletException, IOException {
        if (role.equalsIgnoreCase(Roles.ADMIN)) {
            request.getRequestDispatcher(JSPPages.ADMIN_PAGE).forward(request, response);
        } else if (role.equalsIgnoreCase(Roles.CLIENT)) {
            request.getRequestDispatcher(JSPPages.CLIENT_PAGE).forward(request, response);
        } else if (role.equalsIgnoreCase(Roles.DRIVER)) {
            request.getRequestDispatcher(JSPPages.DRIVER_PAGE).forward(request, response);
        }
    }

    /**
     * Concrete filter logic
     * @param request request
     * @param response response
     * @param filterChain filter chain
     * @param role user role
     * @throws ServletException
     * @throws IOException
     */
    protected abstract void filter(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain,
                                   String role) throws ServletException, IOException;
}
