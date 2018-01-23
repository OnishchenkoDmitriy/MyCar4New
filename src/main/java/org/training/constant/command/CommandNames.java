package org.training.constant.command;

/**
 * All commands names
 */
public interface CommandNames {
    /**
     * Registration and authorization command
     */
    String LOGIN_COMMAND = "login";
    String LOGOUT_COMMAND = "logout";
    String USER_REGISTRATION_COMMAND = "user_registration";
    String DRIVER_REGISTRATION_COMMAND = "driver_registration";

    /**
     * Order command
     */
    String GET_CAR_COMMAND = "make_order";
    String CANCEL_ORDER_COMMAND = "cancel_order";
    String CONFIRM_ORDER_COMMAND = "confirm_order";
    String MY_ORDERS_PAGE_COMMAND = "my_orders";

    /**
     * Other command
     */

    String ALL_USERS_COMMAND = "all_users";
    String ALL_CARS_COMMAND = "all_cars";
    String DELETE_USER_COMMAND = "delete_user";

    /**
     * Redirect commands
     */
    String CONTACT_PAGE_COMMAND = "contact_page";
    String INDEX_PAGE_COMMAND = "index_page";
    String USER_REGISTRATION_PAGE_COMMAND = "user_registration_page";
    String DRIVER_REGISTRATION_PAGE_COMMAND = "driver_registration_page";
    String PROFILE_PAGE_COMMAND = "my_profile_page";
}
