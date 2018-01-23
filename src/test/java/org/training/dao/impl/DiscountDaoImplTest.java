package org.training.dao.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.training.dao.DiscountDao;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.dao.factory.DaoFactory;
import org.training.entity.full.Discounts;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DiscountDaoImplTest {
    @Test
    public void findDiscountsByUserId() throws Exception {
        Integer userId = 1;
        Connection connection = ConnectionPoolHolder.getConnection();
        try(DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao(connection)){
            discountDao.findDiscountsByUserId(userId);
            List<Discounts> discounts = discountDao.findDiscountsByUserId(userId);
            assertNotNull(discounts);
            System.out.println(discounts);
            assertTrue(discounts.size() >= 1);
        }
    }

    @Test
    public void setUserDiscount() throws Exception {
        Integer userId = 1;
        Connection connection = ConnectionPoolHolder.getConnection();
        try(DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao(connection)){
            connection.setAutoCommit(false);
            discountDao.setUserDiscount(Discounts.BY_ORDER_AMOUNT.getId(), userId);
            assertTrue(discountDao.findDiscountsByUserId(userId).contains(Discounts.BY_ORDER_AMOUNT));
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }
    }

    @Test
    public void deleteConcreteUserDiscount() throws Exception {
        Integer userId = 1;
        Connection connection = ConnectionPoolHolder.getConnection();
        try(DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao(connection)){
            connection.setAutoCommit(false);
            discountDao.deleteConcreteUserDiscount(Discounts.BY_ORDER_AMOUNT.getId(), userId);
            assertFalse(discountDao.findDiscountsByUserId(userId).contains(Discounts.BY_ORDER_AMOUNT));
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }
    }

    @Test
    public void deleteAllUserDiscounts() throws Exception {
        Integer userId = 1;
        Connection connection = ConnectionPoolHolder.getConnection();
        try(DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao(connection)){
            connection.setAutoCommit(false);
            discountDao.deleteAllUserDiscounts(userId);
            assertTrue(discountDao.findDiscountsByUserId(userId).isEmpty());
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }
    }

    @Ignore
    @Test
    public void create() throws Exception {
        //TODO
    }

}