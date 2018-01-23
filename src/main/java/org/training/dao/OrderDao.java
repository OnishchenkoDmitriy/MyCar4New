package org.training.dao;

import org.training.entity.full.Order;
import org.training.exception.NoResultFromDBException;

import java.util.List;

public interface OrderDao extends Dao<Order, Integer> {
    /**
     * Finds order by all parameters
     * @param price order price
     * @param clientId client id
     * @param driverId driver id
     * @param departureAddressId departure address id
     * @param arrivalAddressId arrival address id
     * @return order
     * @throws NoResultFromDBException
     */
    Order findOrderByAllParameters(Integer price,
                                   Integer clientId,
                                   Integer driverId,
                                   Integer departureAddressId,
                                   Integer arrivalAddressId) throws NoResultFromDBException;

    /**
     * Finds all orders by user id
     * @param userId user id
     * @return list of orders
     */
    List<Order> findOrdersByUserId(Integer userId) throws NoResultFromDBException;

    /**
     * Deletes all orders by user id
     * @param userId user id
     */
    void deleteAllOrdersByUserId(Integer userId);

    List<Order> findOrdersByDriverId(Integer id) throws NoResultFromDBException;
}
