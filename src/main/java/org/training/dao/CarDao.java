package org.training.dao;

import org.training.entity.full.Car;
import org.training.exception.NoResultFromDBException;

import java.sql.SQLException;

public interface CarDao extends Dao<Car, Integer> {

    /**
     * Finds car by number
     * @param number car number
     * @return car
     * @throws NoResultFromDBException
     */
    Car findByCarNumber(String number) throws NoResultFromDBException;

    /**
     * Finds free car by type
     * @param carType car type
     * @return car
     * @throws NoResultFromDBException
     */
    Car findFreeCarByType(String carType) throws NoResultFromDBException;

    /**
     * Updates car state by car id
     * @param carId car id
     * @param carState car state
     */
    void updateCarState(Integer carId, String carState) throws SQLException;

}
