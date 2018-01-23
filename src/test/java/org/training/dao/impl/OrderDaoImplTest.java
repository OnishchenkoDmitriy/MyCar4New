package org.training.dao.impl;

import org.junit.Test;
import org.training.dao.OrderDao;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.dao.factory.DaoFactory;
import org.training.entity.full.Address;
import org.training.entity.full.Car;
import org.training.entity.full.Order;
import org.training.entity.full.User;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderDaoImplTest {

    @Test
    public void findOrderByAllParameters() throws Exception {
        Integer price = 160;
        Integer clientId = 1;
        Integer driverId = 6;
        Integer departureAddressId = 1;
        Integer arrivalAddressId = 2;

        Connection connection = ConnectionPoolHolder.getConnection();
        try(OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection)){
            Order order = orderDao.findOrderByAllParameters(
                    price, clientId, driverId, departureAddressId, arrivalAddressId);
            assertNotNull(order);
            assertEquals(clientId, order.getClient().getId());
            assertEquals(driverId, order.getDriver().getId());
        }
    }

    @Test
    public void findOrdersByUserId() throws Exception {
        Integer userId = 1;
        Connection connection = ConnectionPoolHolder.getConnection();
        try(OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection)){
            List<Order> orders = orderDao.findOrdersByUserId(userId);
            assertNotNull(orders);
            assertTrue(orders.size() >= 1);
            for (Order order : orders) {
                assertTrue(userId == order.getClient().getId());
            }
        }
    }

    @Test
    public void findOrdersByDriverId() throws Exception {
        Integer driverId = 2;
        Connection connection = ConnectionPoolHolder.getConnection();
        try(OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection)){
            List<Order> orders = orderDao.findOrdersByDriverId(driverId);
            assertNotNull(orders);
            assertTrue(orders.size() >= 1);
            for (Order order : orders) {
                assertTrue(driverId == order.getDriver().getId());
            }
        }
    }

    @Test
    public void deleteAllOrdersByUserId() throws Exception {
        Integer userId = 1;
        Connection connection = ConnectionPoolHolder.getConnection();
        try(OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection)) {
            connection.setAutoCommit(false);
            orderDao.deleteAllOrdersByUserId(userId);
            assertTrue(orderDao.findOrdersByUserId(userId).isEmpty());
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }
    }

    @Test
    public void create() throws Exception {
        Integer price = 111;
        Integer clientId = 1;
        Integer driverId = 2;
        Integer carId = 1;
        Integer departureAddressId = 1;
        Integer arrivalAddressId = 2;

        Car car = mock(Car.class);
        when(car.getId()).thenReturn(carId);

        User client = mock(User.class);
        when(client.getId()).thenReturn(clientId);

        User driver = mock(User.class);
        when(driver.getId()).thenReturn(driverId);

        Address departureAddress = mock(Address.class);
        when(departureAddress.getId()).thenReturn(departureAddressId);

        Address arrivalAddress = mock(Address.class);
        when(arrivalAddress.getId()).thenReturn(arrivalAddressId);

        Order order = mock(Order.class);
        when(order.getPrice()).thenReturn(price);
        when(order.getCar()).thenReturn(car);
        when(order.getClient()).thenReturn(client);
        when(order.getDriver()).thenReturn(driver);
        when(order.getDepartureAddress()).thenReturn(departureAddress);
        when(order.getArrivalAddress()).thenReturn(arrivalAddress);

        Connection connection = ConnectionPoolHolder.getConnection();
        try(OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection)) {
            connection.setAutoCommit(false);
            orderDao.create(order);
            assertNotNull(orderDao.findOrderByAllParameters(
                    price, clientId, driverId, departureAddressId, arrivalAddressId
            ));
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }
    }

}