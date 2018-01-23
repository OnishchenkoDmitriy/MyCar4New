package org.training.constant.jsp;

/**
 * Ways to jsp pages
 */
public interface JSPPages {

    /**
     * public pages
     */
    String INDEX_PAGE = "/WEB-INF/index.jsp";
    String CONTACT_PAGE = "/WEB-INF/contact.jsp";
    String ERROR_PAGE = "/WEB-INF/error.jsp";
    /**
     * jspAuthorized pages
     */
    String ADMIN_PAGE = "/WEB-INF/jspAuthorized/admin_page.jsp";
    String CLIENT_PAGE = "/WEB-INF/jspAuthorized/client_page.jsp";
    String DRIVER_PAGE = "/WEB-INF/jspAuthorized/driver_page.jsp";
    String PROFILE_PAGE = "/WEB-INF/jspAuthorized/my_profile_page.jsp";
    String MY_ORDERS_PAGE = "/WEB-INF/jspAuthorized/my_orders_page.jsp";
    String CURRENT_ORDER_PAGE = "/WEB-INF/jspAuthorized/current_order_page.jsp";
    String ALL_USERS_PAGE = "/WEB-INF/jspAuthorized/all_users_page.jsp";
    String ALL_CARS_PAGE = "/WEB-INF/jspAuthorized/all_cars_page.jsp";
    /**
     * jspRegistration pages
     */
    String USER_REG_PAGE = "/WEB-INF/jspRegistration/userRegistration.jsp";
    String DRIVER_REG_PAGE = "/WEB-INF/jspRegistration/driverRegistration.jsp";

}
