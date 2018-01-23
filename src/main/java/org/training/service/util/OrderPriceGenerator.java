package org.training.service.util;

import org.training.constant.global.GlobalConstants;
import org.training.dao.DiscountDao;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.dao.factory.DaoFactory;
import org.training.entity.full.Car;
import org.training.entity.full.Discounts;
import org.training.entity.full.User;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderPriceGenerator {

    /**
     * Finds all user discounts and calculate sum of discounts in percent
     * @param user user
     * @return sum of discounts in percent
     */
    private static Integer getTotalDiscountInPercent(User user){
       /* Integer totalDiscount = 0;
        Connection connection = ConnectionPoolHolder.getConnection();
        try(DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao(connection)){
            for (Discounts discount: user.getDiscounts()){
                totalDiscount += discount.getValueInPercent();
                if(discount.getId() == Discounts.BY_ORDER_AMOUNT.getId()){
                    discountDao.deleteConcreteUserDiscount(Discounts.BY_ORDER_AMOUNT, user);
                }
            }
            return totalDiscount;
        }catch (Exception e){
            return 1;
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
*/
       return 10;
    }


    /**
     *
     * @param user user
     * @param carType car type
     * @return price of order depends on car type user discount
     * and distance from departure address to arrival address.
     */
    public static Integer getOrderPrice(User user, String carType){
        Integer distanceInKm = (int)Math.ceil(Math.random()
                * (GlobalConstants.MAX_DISTANCE - GlobalConstants.MIN_DISTANCE- 1)
                + GlobalConstants.MIN_DISTANCE);

        Integer pricePerKm;
        if(carType.equalsIgnoreCase(Car.CarTypes.PREMIUM.toString())){
            pricePerKm = GlobalConstants.PREMIUM_PRICE;
        }else {
            pricePerKm = GlobalConstants.CLASSIC_PRICE;
        }
        Integer orderPrice = (distanceInKm
                * pricePerKm
                * getTotalDiscountInPercent(user));
        return orderPrice;
    }
}
