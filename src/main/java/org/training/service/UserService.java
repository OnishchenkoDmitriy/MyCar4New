package org.training.service;

import org.training.entity.full.Car;
import org.training.entity.full.User;
import org.training.exception.NoResultFromDBException;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends Service {

    User findByEmail(String email) throws NoResultFromDBException;
    void registerUser(User user) throws Exception;
    void registerDriver(User driver, Car car) throws SQLException;
    void deleteDriverById(Integer id) throws SQLException;
    void deleteUserById(Integer id) throws SQLException;
    List<User> findAllUsers() throws NoResultFromDBException;
    List<Car> findAllCars() throws NoResultFromDBException;
    Car findCarByNumber(String number) throws NoResultFromDBException;
}
