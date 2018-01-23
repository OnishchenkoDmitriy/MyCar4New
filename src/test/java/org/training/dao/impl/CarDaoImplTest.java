package org.training.dao.impl;

import org.junit.Test;
import org.training.dao.CarDao;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.dao.factory.DaoFactory;
import org.training.entity.full.Car;
import org.training.entity.full.User;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarDaoImplTest {

    @Test
    public void create() throws Exception {
        String number = "AA0000II";
        String brand = "AudiTest";
        String model = "A6Test";
        String color = "RED";
        String carState = Car.CarStates.FREE.toString();
        String carType = Car.CarTypes.PREMIUM.toString();
        Integer driverId = 1;

        User driver = mock(User.class);
        when(driver.getId()).thenReturn(driverId);

        Car car = mock(Car.class);
        when(car.getNumber()).thenReturn(number);
        when(car.getBrand()).thenReturn(brand);
        when(car.getModel()).thenReturn(model);
        when(car.getColor()).thenReturn(color);
        when(car.getCarState()).thenReturn(carState);
        when(car.getCarType()).thenReturn(carType);
        when(car.getDriver()).thenReturn(driver);

        Connection connection = ConnectionPoolHolder.getConnection();
        try(CarDao carDao = DaoFactory.getInstance().createCarDao(connection)){
            connection.setAutoCommit(false);
            carDao.create(car);
            assertNotNull(carDao.findByCarNumber(car.getNumber()));
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }

    }

    @Test
    public void updateCarState() throws Exception {
        Integer carId = 1;
        Connection connection = ConnectionPoolHolder.getConnection();
        try(CarDao carDao = DaoFactory.getInstance().createCarDao(connection)){
            connection.setAutoCommit(false);
            carDao.updateCarState(carId, Car.CarStates.FREE.toString());
            assertEquals(Car.CarStates.FREE.toString(),
                    carDao.findById(carId).getCarState());
            carDao.updateCarState(carId, Car.CarStates.BUSY.toString());
            assertEquals(Car.CarStates.BUSY.toString(),
                    carDao.findById(carId).getCarState());
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }
    }

    @Test
    public void findByCarNumber() throws Exception {
        String carNumber = "AA3456BB";
        Connection connection = ConnectionPoolHolder.getConnection();
        try(CarDao carDao = DaoFactory.getInstance().createCarDao(connection)){
            Car car = carDao.findByCarNumber(carNumber);
            assertNotNull(car);
            assertEquals(carNumber, car.getNumber());
        }
    }

    @Test
    public void findFreeCarByType() throws Exception {
        Connection connection = ConnectionPoolHolder.getConnection();
        try(CarDao carDao = DaoFactory.getInstance().createCarDao(connection)){
            Car car = carDao.findFreeCarByType(Car.CarTypes.CLASSIC.toString());
            assertNotNull(car);
            assertEquals(Car.CarTypes.CLASSIC.toString(), car.getCarType());
            assertEquals(Car.CarStates.FREE.toString(), car.getCarState());
        }
    }

}