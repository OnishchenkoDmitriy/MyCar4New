package org.training.constant.db;

/**
 * All params from tables
 */
public interface TableParameters {

    String ID = "id";

    /**
     * Params from user table
     */
    interface UserParam{
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String PHONE_NUMBER = "phoneNumber";
        String EMAIL = "email";
        String PASSWORD = "password";
        String ROLE = "role";
    }

    /**
     * Params from user_fas_discount table
     */
    interface UserHasDiscount{
        String USER_ID = "user_id";
        String DISCOUNT_ID = "discount_id";
    }

    /**
     * Params from discount table
     */
    interface DiscountParam{
        String NAME = "name";
    }

    /**
     * Params from car table
     */
    interface CarParam{
        String NUMBER = "number";
        String BRAND = "brand";
        String MODEL = "model";
        String COLOR = "color";
        String STATE = "state";
        String TYPE = "type";
        String DRIVER_ID = "driver_id";
    }

    /**
     * Params from order table
     */
    interface OrderParam{
        String PRICE = "price";
        String CLIENT_ID = "client_id";
        String DRIVER_ID = "driver_id";
        String CAR_ID = "car_id";
        String DEPARTURE_ADDRESS_ID = "departure_address_id";
        String ARRIVAL_ADDRESS_ID = "arrival_address_id";
    }

    /**
     * Params from address table
     */
    interface AddressParam{
        String CITY = "city";
        String STREET = "street";
        String NUMBER = "number";
    }
}
