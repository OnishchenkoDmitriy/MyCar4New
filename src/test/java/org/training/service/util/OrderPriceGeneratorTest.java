package org.training.service.util;

import org.junit.Before;
import org.junit.Test;
import org.training.dao.DiscountDao;
import org.training.entity.full.Car;
import org.training.entity.full.Discounts;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderPriceGeneratorTest {

    Integer userId;
    List<Discounts> discounts;
    DiscountDao discountDao;
    Car.CarTypes carType;


    @Before
    public void setUp() throws Exception {
        userId = 1;
        discounts = new ArrayList<Discounts>();
        discounts.add(Discounts.ADMIN_DISCOUNT);
        discounts.add(Discounts.BY_ORDER_AMOUNT);
        discountDao = mock(DiscountDao.class);
        carType = Car.CarTypes.CLASSIC;
    }

    @Test
    public void getTotalDiscountInPercent() throws Exception {
        when(discountDao.findDiscountsByUserId(anyInt())).thenReturn(discounts);
        Integer discountInPercent = OrderPriceGenerator.getTotalDiscountInPercent(discountDao, userId);
        assertEquals((Integer)90, discountInPercent);
    }

    @Test
    public void getOrderPrice() throws Exception {
        when(discountDao.findDiscountsByUserId(anyInt())).thenReturn(discounts);
        Integer totalDiscount = OrderPriceGenerator.getTotalDiscountInPercent(discountDao, userId);
        Integer fullPrice = OrderPriceGenerator.getOrderPrice(totalDiscount, carType.toString());
        System.out.println(fullPrice);
    }

}