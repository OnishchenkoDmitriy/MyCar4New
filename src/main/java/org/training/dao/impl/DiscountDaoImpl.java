package org.training.dao.impl;

import org.training.constant.db.TableParameters;
import org.training.constant.messages.LogMessages;
import org.training.dao.AbstractDao;
import org.training.dao.DiscountDao;
import org.training.dao.util.QueryContainer;
import org.training.entity.full.Discounts;
import org.training.exception.NoResultFromDBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DiscountDaoImpl extends AbstractDao<Discounts> implements DiscountDao {


    public DiscountDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected Discounts extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(TableParameters.UserHasDiscount.DISCOUNT_ID);
        if (id == Discounts.BY_ORDER_AMOUNT.getId()) {
            return Discounts.BY_ORDER_AMOUNT;
        } else if (id == Discounts.ADMIN_DISCOUNT.getId()) {
            return Discounts.ADMIN_DISCOUNT;
        }
        return null;
    }

    @Override
    public List<Discounts> findDiscountsByUserId(Integer userId) throws NoResultFromDBException {
        String query = QueryContainer.INSTANCE.findDiscountsByUserId(userId);
        return findAllByQuery(query);
    }

    @Override
    public void setUserDiscount(Integer discountId, Integer userId) {
        String query = QueryContainer.INSTANCE.setUserDiscount(discountId, userId);
        try {
            executeQuery(query);
        } catch (SQLException e) {
            logger.error(e.getMessage() + " " + LogMessages.SET_USER_DISCOUNT_ERROR);
        }
    }

    @Override
    public void deleteConcreteUserDiscount(Integer discountId, Integer userId) {
        String query = QueryContainer.INSTANCE.deleteUserDiscount(discountId, userId);
        try {
            executeQuery(query);
        } catch (SQLException e) {
            logger.error(e.getMessage() + " " + LogMessages.DELETE_USER_DISCOUNT_ERROR);
        }
    }

    @Override
    public void deleteAllUserDiscounts(Integer userId) {
        String query = QueryContainer.INSTANCE.deleteAllUserDiscounts(userId);
        try {
            executeQuery(query);
        } catch (SQLException e) {
            logger.error(e.getMessage() + " " + LogMessages.DELETE_ALL_USER_DISCOUNT_ERROR);
        }
    }

    @Override
    public void create(Discounts entity) {
        //TODO
    }

}
