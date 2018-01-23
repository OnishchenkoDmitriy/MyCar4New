package org.training.dao.impl;

import org.training.constant.db.TableParameters;
import org.training.constant.messages.LogMessages;
import org.training.dao.AbstractDao;
import org.training.dao.OrderDao;
import org.training.dao.util.QueryContainer;
import org.training.entity.full.Order;
import org.training.entity.lazy.OrderLazy;
import org.training.exception.NoResultFromDBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.training.constant.db.TableParameters.OrderParam.PRICE;


public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {


    public OrderDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(TableParameters.ID);
        Integer price = resultSet.getInt(PRICE);
        return new OrderLazy.OrderBuilder()
                .setId(id)
                .setPrice(price)
                .buildLazy();
    }

    @Override
    public Order findOrderByAllParameters(Integer price,
                                          Integer clientId, Integer driverId,
                                          Integer departureAddressId, Integer arrivalAddressId)
            throws NoResultFromDBException {
        String query = QueryContainer.INSTANCE.findOrderByAllParameters(price, clientId,
                driverId, departureAddressId, arrivalAddressId);
        return findOneByQuery(query);
    }

    @Override
    public List<Order> findOrdersByUserId(Integer userId) throws NoResultFromDBException {
        return findAllByParameter(TableParameters.OrderParam.CLIENT_ID, userId.toString());
    }

    @Override
    public List<Order> findOrdersByDriverId(Integer driverId) throws NoResultFromDBException {
        return findAllByParameter(TableParameters.OrderParam.DRIVER_ID, driverId.toString());
    }

    @Override
    public void deleteAllOrdersByUserId(Integer userId) {
        deleteByParameter(TableParameters.OrderParam.CLIENT_ID, userId.toString());
    }



    @Override
    public void create(Order order) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO my_car_3.order (`price`, `car_id`, `client_id`, `driver_id`, `departure_address_id`, `arrival_address_id`) "
                        + "VALUES (?, ?, ?, ?, ?, ?);")) {
            ps.setInt(1, order.getPrice());
            ps.setInt(2, order.getCar().getId());
            ps.setInt(3, order.getClient().getId());
            ps.setInt(4, order.getDriver().getId());
            ps.setInt(5, order.getDepartureAddress().getId());
            ps.setInt(6, order.getArrivalAddress().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage() + " " + LogMessages.CREATE_ORDER_ERROR);
            throw new SQLException();
        }
    }
}
