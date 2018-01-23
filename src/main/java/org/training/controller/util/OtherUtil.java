package org.training.controller.util;

import org.training.constant.global.Roles;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Other util for controller
 */
public class OtherUtil {

    /**
     * Check user role from request and return home page depends on role.
     * @param request
     * @return
     */
    public static String getUserIndexPage(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute(RequestAttributes.ROLE);
        if (role.equalsIgnoreCase(Roles.ADMIN)) {
            return JSPPages.ADMIN_PAGE;
        } else if (role.equalsIgnoreCase(Roles.CLIENT)) {
            return JSPPages.CLIENT_PAGE;
        } else if (role.equalsIgnoreCase(Roles.DRIVER)) {
            return JSPPages.DRIVER_PAGE;
        }else {
            return JSPPages.INDEX_PAGE;
        }
    }
}
