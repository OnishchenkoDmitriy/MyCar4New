package org.training.dao;

import org.training.entity.full.Discounts;
import org.training.exception.NoResultFromDBException;

import java.util.List;

public interface DiscountDao extends Dao<Discounts, Integer> {

    /**
     *Finds all discounts by user id
     * @param userId user id
     * @return list of discounts
     */
    List<Discounts> findDiscountsByUserId(Integer userId) throws NoResultFromDBException;

    /**
     * Sets discount to user
     * @param discountId
     * @param userId
     */
    void setUserDiscount(Integer discountId, Integer userId);

    /**
     * Deletes discount from user
     * @param discountId
     * @param userId
     */
    void deleteConcreteUserDiscount(Integer discountId, Integer userId);

    /**
     * Deletes all discounts from user
     * @param userId user id
     */
    void deleteAllUserDiscounts(Integer userId);

}
