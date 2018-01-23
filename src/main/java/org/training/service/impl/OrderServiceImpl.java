package org.training.service.impl;

import org.training.constant.global.GlobalConstants;
import org.training.dao.*;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.dao.factory.DaoFactory;
import org.training.entity.full.*;
import org.training.exception.NoFreeCarSuchTypeException;
import org.training.exception.NoResultFromDBException;
import org.training.service.OrderService;
import org.training.service.util.OrderPriceGenerator;
import org.training.util.logUtil.LogMessageBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class OrderServiceImpl implements OrderService {

    private OrderServiceImpl() {
    }

    private static final class OrderServiceImplHolder {
        private static final OrderServiceImpl instance = new OrderServiceImpl();
    }

    public static OrderServiceImpl getInstance() {
        return OrderServiceImplHolder.instance;
    }

    @Override
    public Order makeOrder(User client, Address departureAddress,
                           Address arrivalAddress, String carType) throws Exception {

        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = daoFactory.createCarDao(connection);
             OrderDao orderDao = daoFactory.createOrderDao(connection);
             UserDao userDao = daoFactory.createUserDao(connection);
             AddressDao addressDao = daoFactory.createAddressDao(connection)) {

            connection.setAutoCommit(false);

            Car car = carDao.findFreeCarByType(carType);
            if (Objects.isNull(car)) {
                throw new NoFreeCarSuchTypeException();
            }
            carDao.updateCarState(car.getId(), Car.CarStates.BUSY.toString());
            addressDao.create(arrivalAddress);
            addressDao.create(departureAddress);

            User newClient = userDao.findByEmail(client.getEmail());
            Address newDepartureAddress = addressDao.findAddressByStreetAndNumber(departureAddress.getStreet(),
                    departureAddress.getNumber());
            Address newArrivalAddress = addressDao.findAddressByStreetAndNumber(arrivalAddress.getStreet(),
                    arrivalAddress.getNumber());

            Order order = new Order.OrderBuilder()
                    .setDepartureAddress(newDepartureAddress)
                    .setArrivalAddress(newArrivalAddress)
                    .setPrice(OrderPriceGenerator.getOrderPrice(newClient, car.getCarType()))
                    .setDriver(car.getDriver())
                    .setClient(newClient)
                    .setCar(car)
                    .build();


            orderDao.create(order);
            checkUserDiscounts(newClient);
            Order newOrder = orderDao.findOrderByAllParameters(order.getPrice(),
                    order.getClient().getId(),
                    order.getDriver().getId(),
                    order.getDepartureAddress().getId(),
                    order.getArrivalAddress().getId()
            );

            connection.commit();
            return newOrder;
        } catch (SQLException e) {
            connection.rollback();
        }
        return null;
    }

    @Override
    public void cancelOrder(Order order) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection);
             CarDao carDao = DaoFactory.getInstance().createCarDao(connection);) {
            connection.setAutoCommit(false);

            carDao.updateCarState(order.getCar().getId(), Car.CarStates.FREE.toString());
            orderDao.delete(order.getId());

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }
    }

    @Override
    public List getAllOrders(User user) throws NoResultFromDBException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection)) {
            List<Order> orders;
            if (user.getRole().equalsIgnoreCase(User.Roles.DRIVER.toString())) {
                orders = orderDao.findOrdersByDriverId(user.getId());
            } else {
                orders = orderDao.findOrdersByUserId(user.getId());
            }
            return orders;
        } catch (Exception e) {
            throw new NoResultFromDBException();
        }
    }

    @Override
    public void checkUserDiscounts(User user) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (DiscountDao discountDao = daoFactory.createDiscountDao(connection)) {
            List<Discounts> discounts = discountDao.findDiscountsByUserId(user.getId());
            if (Objects.isNull(discounts)) {
                return;
            }
            if (getAllOrders(user).size() % (GlobalConstants.ORDERS_AMOUNT_FOR_DISCOUNT - 1) == 0) {
                discountDao.setUserDiscount(Discounts.BY_ORDER_AMOUNT.getId(), user.getId());
            }
        } catch (Exception e) {
            logger.info(LogMessageBuilder.INSTANCE.userHasNoDiscountsYet(user));
        }

    }
}
