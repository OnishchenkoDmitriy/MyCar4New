package org.training.constant.jsp;

/**
 * All attributes for jsp pages
 */
public interface RequestAttributes {

    /**
     * user attributes
     */
    String USER = "user";
    String ROLE = "role";
    String USER_NAME = "userName";

    /**
     * message attributes
     */
    String INFORMATION_MESSAGE = "informationMessage";
    String ERROR_MESSAGE = "errorMessage";
    String ERROR_DATA_MESSAGE = "errorDataMessage";

    /**
     * orders attributes
     */
    String MY_ORDERS = "orders";
    String CURRENT_ORDER = "currentOrder";
    String ORDER = "order";

    /**
     * registration and authorization attributes
     */
    String REGISTRATION_IS_SUCCESSFUL = "registrationIsSuccessful";
    String LOG_OUT = "logOut";
    String LANGUAGE = "language";
    String NO_LANGUAGE = "isLanguage";

    /**
     * result lists attributes
     */
    String CAR_TYPE = "carType";
    String ALL_USERS = "users";
    String ALL_CARS = "cars";
}
