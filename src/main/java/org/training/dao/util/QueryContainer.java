package org.training.dao.util;

import org.training.constant.db.TableName;
import org.training.constant.db.TableParameters;

/**
 * Query builder
 */
public enum QueryContainer {

    INSTANCE;

    /**
     *
     * @param tableName name of table
     * @return query to find all entries in table
     */
    public String findAllFromTable(String tableName){
        return "SELECT * FROM " + tableName;
    }

    /**
     *
     * @param tableName name of table
     * @param id id
     * @return query to find all entries from table by id
     */
    public String findFromTableById(String tableName, Integer id){
        return "SELECT * FROM " + tableName + " WHERE id = " + id;
    }

    /**
     *
     * @param tableName name of table
     * @param id id
     * @return query to delete entry from table
     */
    public String deleteFromTableById(String tableName, Integer id){
        return "DELETE FROM " + tableName + " WHERE id = \"" + id + "\"";
    }

    /**
     *
     * @param tableName name of table
     * @param parameterName name of parameter
     * @param parameterValue parameter value
     * @return query to delete entry from table by parameter
     */
    public String deleteFromTableByParameter(String tableName, String parameterName,
                                             String parameterValue){
        return "DELETE FROM " + tableName + " WHERE "
                + parameterName + " = \"" + parameterValue + "\";";
    }

    /**
     *
     * @param tableName name of table
     * @param parameterName name of parameter
     * @param parameterValue parameter value
     * @return query to find all entries from table by parameter
     */
    public String findAllFromTableByParameter(String tableName, String parameterName, String parameterValue){
        return "SELECT * FROM " + tableName + " WHERE " + parameterName + " = \"" + parameterValue + "\";";
    }

    /**
     *
     * @param tableName name of table
     * @param entityId entity id
     * @param idName id name
     * @return query to find foreign key id from table
     */
    public String findForeignKeyIdFromTable(String tableName, String entityId, String idName){
        return "SELECT " + idName + " FROM " + tableName + " WHERE id = \"" + entityId + "\";";
    }

    /**
     *
     * @param carType car type
     * @return query to find free car by type
     */
    public String findFreeCarByType(String carType){
        return "SELECT * FROM " + TableName.CAR_TABLE +
                " WHERE state=\"FREE\" AND type=\"" + carType + "\"";
    }

    /**
     *
     * @param street street name
     * @param number street number
     * @return query to find address by street name and street number
     */
    public String findAddressByStreetAndNumber(String street, String number){
        return "SELECT * FROM " + TableName.ADDRESS_TABLE
                + " WHERE street = \"" + street
                + "\" AND number = \"" + number + "\";";

    }

    /**
     *
     * @param price order price
     * @param clientId client id
     * @param driverId driver id
     * @param departureAddressId departure address id
     * @param arrivalAddressId arrival address id
     * @return query to find order by all parameter
     */
    public String findOrderByAllParameters(Integer price, Integer clientId, Integer driverId, Integer departureAddressId, Integer arrivalAddressId) {
        /*return "SELECT * FROM my_car_3.order WHERE price = \""
                + price + "\" AND client_id = \"" + clientId
                + "\" AND driver_id = \"" + driverId
                + "\" AND departure_address_id = \"" + departureAddressId
                + "\" AND arrival_address_id = \"" + arrivalAddressId + "\"";*/

        return "SELECT * FROM my_car_3.order WHERE price=" + price
                + " AND client_id=" + clientId
                + " AND driver_id=" + driverId
                + " AND departure_address_id=" + departureAddressId
                + " AND arrival_address_id=" + arrivalAddressId;
    }

    /**
     *
     * @param userId user id
     * @return query to find all discounts by user id
     */
    public String findDiscountsByUserId(Integer userId){
        return "SELECT * FROM " + TableName.USER_HAS_DISCOUNT_TABLE
                + " WHERE User_id=\"" + userId + "\"";
    }

    /**
     *
     * @param discountId discount id
     * @param userId user id
     * @return query to set discount to user
     */
    public String setUserDiscount(Integer discountId, Integer userId){
        return "INSERT INTO " + TableName.USER_HAS_DISCOUNT_TABLE
                + " (User_id, Discount_id) VALUES (" + userId + "," + discountId + ")";
    }

    /**
     * @param discountId discount id
     * @param userId user id
     * @return query to delete discount from user
     */
    public String deleteUserDiscount(Integer discountId, Integer userId){
        return "DELETE FROM " + TableName.USER_HAS_DISCOUNT_TABLE + " WHERE "
                + TableParameters.UserHasDiscount.DISCOUNT_ID + " = " + discountId
                + " AND " + TableParameters.UserHasDiscount.USER_ID + " = " + userId;
    }

    /**
     * Deletes all user discounts
     * @param userId user id
     * @return query to delete all user discounts
     */
    public String deleteAllUserDiscounts(Integer userId){
        return "DELETE FROM " + TableName.USER_HAS_DISCOUNT_TABLE + " WHERE "
                + TableParameters.UserHasDiscount.USER_ID + " = " + userId;
    }


}
