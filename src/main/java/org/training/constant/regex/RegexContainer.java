package org.training.constant.regex;

/**
 * Regex container
 */
public interface RegexContainer {
    /**
     * User validation
     */
    String REGEX_EMAIL = "email.regexp";
    String REGEX_NAME = "name.regexp";
    String REGEX_LAST_NAME = "last.name.regexp";
    String REGEX_PHONE_NUMBER = "phone.number.regexp";
    String REGEX_PASSWORD = "password.regexp";
    /**
     * Car validation
     */
    String CAR_NUMBER_REGEX = "car.number.regexp";
    String CAR_BRAND_REGEX = "car.brand.regexp";
    String CAR_MODEL_REGEX = "car.model.regexp";
    /**
     * Address validation
     */
    String CITY_REGEX = "city.regexp";
    String STREET_REGEX = "street.regexp";
    String STREET_NUMBER_REGEX = "street.number.regexp";
}
