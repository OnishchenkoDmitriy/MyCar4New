package org.training.controller.util;

import org.training.constant.global.GlobalConstants;
import org.training.constant.global.Roles;
import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static org.training.constant.global.GlobalConstants.ENTRY_PER_PAGE;

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

    /**
     *
     * @param request request
     * @param list list
     * @return sub list from list for pagination depends on current page
     */
    public static List getSubListForPagination (HttpServletRequest request,
                                         List list){

        Integer numberOfPages = (int)Math.ceil((double) list.size() / ENTRY_PER_PAGE);
        String page = request.getParameter(RequestAttributes.CURRENT_PAGE);

        Integer currentPage;
        if(Objects.isNull(page)){
            currentPage = 1;
        }else{
            currentPage = Integer.valueOf(page);
        }
        Integer lastIndex = GlobalConstants.ENTRY_PER_PAGE * currentPage;
        Integer firstIndex = lastIndex - GlobalConstants.ENTRY_PER_PAGE;

        if(lastIndex > list.size()){
            lastIndex = list.size();
        }

        request.setAttribute(RequestAttributes.NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(RequestAttributes.FIRST_INDEX, firstIndex);

        return list.subList(firstIndex, lastIndex);
    }
}
