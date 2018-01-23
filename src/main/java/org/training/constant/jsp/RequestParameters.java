package org.training.constant.jsp;

/**
 * All params from jsp
 */
public interface RequestParameters {
    /**
     * user params
     */
    String EMAIL = "email";
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    String PHONE_NUMBER = "phoneNumber";
    String PASSWORD = "password";
    String PASSWORD2 = "password2";
    String CURRENT_USER_ID = "currentUserId";
    /**
     * car params
     */
    String CAR_NUMBER = "carNumber";
    String CAR_BRAND = "carBrand";
    String CAR_MODEL = "carModel";
    String CAR_COLOR = "carColor";
    String CAR_TYPE = "carType";
    /**
     * address params
     */
    String CITY = "city";
    String DEPARTURE_STREET = "departureStreet";
    String ARRIVAL_STREET = "arrivalStreet";
    String DEPARTURE_NUMBER = "departureNumber";
    String ARRIVAL_NUMBER = "arrivalNumber";
}
