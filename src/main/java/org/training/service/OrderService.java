package org.training.service;

import org.training.entity.full.Address;
import org.training.entity.full.Order;
import org.training.entity.full.User;
import org.training.exception.NoResultFromDBException;

import java.sql.SQLException;
import java.util.List;

public interface OrderService extends Service {
    Order makeOrder(User client, Address departureAddress,
                    Address arrivalAddress, String carType) throws Exception;
    void cancelOrder(Order order) throws SQLException;

    List getAllOrders(User user) throws NoResultFromDBException;
    void checkUserDiscounts(User user);
}
