package org.training.constant.messages;

/**
 * Validation error messages
 */
public interface ValidationMessages {
    /**
     * user validation
     */
    String WRONG_EMAIL = "wrong.email.format";
    String WRONG_NAME = "wrong.name.format";
    String WRONG_LAST_NAME = "wrong.last.name.format";
    String WRONG_PHONE_NUMBER = "wrong.phone.number.format";
    String WRONG_PASSWORD = "wrong.password.format";
    String WRONG_PASSWORD2 = "wrong.password2.format";
    /**
     * car validation
     */
    String WRONG_CAR_NUMBER = "wrong.car.number.format";
    String WRONG_CAR_BRAND = "wrong.car.brand.format";
    String WRONG_CAR_MODEL = "wrong.car.model.format";
    /**
     * address validation
     */
    String WRONG_CITY = "wrong.city.format";
    String WRONG_DEPARTURE_STREET = "wrong.departure.street.format";
    String WRONG_ARRIVAL_STREET = "wrong.arrival.street.format";
    String WRONG_DEPARTURE_NUMBER = "wrong.departure.number.format";
    String WRONG_ARRIVAL_NUMBER = "wrong.arrival.number.format";
}
