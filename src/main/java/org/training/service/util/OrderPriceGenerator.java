package org.training.service.util;

import org.training.constant.global.GlobalConstants;
import org.training.dao.DiscountDao;
import org.training.entity.full.Car;
import org.training.entity.full.Discounts;
import org.training.exception.NoResultFromDBException;

import java.util.List;

public class OrderPriceGenerator {

    /**
     * Finds all user discounts and calculate sum of discounts in percent
     * @param discountDao
     * @param userId
     * @return
     */
    public static Integer getTotalDiscountInPercent(DiscountDao discountDao, Integer userId){
        Integer totalDiscount = 0;
        List<Discounts> userDiscounts = null;
        try {
            userDiscounts = discountDao.findDiscountsByUserId(userId);
            for (Discounts discount: userDiscounts){
                totalDiscount += discount.getValueInPercent();
                if(discount.getId() == Discounts.BY_ORDER_AMOUNT.getId()){
                    discountDao.deleteConcreteUserDiscount(Discounts.BY_ORDER_AMOUNT.getId(), userId);
                }
            }
            return totalDiscount;
        } catch (NoResultFromDBException e) {
            return 0;
        }
    }

    /**
     * @param totalDiscount total discount
     * @param carType car type
     * @return price of order depends on car type user discount
     * and distance from departure address to arrival address.
     */
    public static Integer getOrderPrice(Integer totalDiscount, String carType){
        Integer distanceInKm = (int)Math.ceil(Math.random()
                * (GlobalConstants.MAX_DISTANCE - GlobalConstants.MIN_DISTANCE- 1)
                + GlobalConstants.MIN_DISTANCE);

        Integer pricePerKm;
        if(carType.equalsIgnoreCase(Car.CarTypes.PREMIUM.toString())){
            pricePerKm = GlobalConstants.PREMIUM_PRICE;
        }else {
            pricePerKm = GlobalConstants.CLASSIC_PRICE;
        }

        Integer orderPrice = distanceInKm * pricePerKm;
        Integer discount = orderPrice * (totalDiscount / 100);

        return (int)Math.ceil(orderPrice - discount);
    }
}
