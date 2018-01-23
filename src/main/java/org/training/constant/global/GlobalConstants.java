package org.training.constant.global;

public interface GlobalConstants {
    /**
     * maximal distance between departure address and arrival address in order
     */
    Integer MAX_DISTANCE = 50;
    /**
     * minimal distance between departure address and arrival address in order
     */
    Integer MIN_DISTANCE = 5;
    /**
     * value means that user can get discount every fifth trip
     */
    Integer ORDERS_AMOUNT_FOR_DISCOUNT = 5;
    /**
     * price per km depends for classic car type
     */
    Integer CLASSIC_PRICE = 10;
    /**
     * price per km depends for premium car type
     */
    Integer PREMIUM_PRICE = 20;
}
