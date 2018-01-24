package org.training.service.impl;

import org.training.constant.db.TableParameters;
import org.training.constant.messages.LogMessages;
import org.training.dao.CarDao;
import org.training.dao.OrderDao;
import org.training.dao.UserDao;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.dao.factory.DaoFactory;
import org.training.entity.full.Car;
import org.training.entity.full.User;
import org.training.exception.NoResultFromDBException;
import org.training.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private UserServiceImpl() {
    }

    private static final class UserServiceImplHolder {
        private static final UserServiceImpl instance = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.instance;
    }

    /**
     * Finds user by email
     * @param email
     * @return
     * @throws NoResultFromDBException
     */
    @Override
    public User findByEmail(String email) throws NoResultFromDBException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = daoFactory.createUserDao(connection)) {
            User user = userDao.findByEmail(email);
            if (Objects.isNull(user)) {
                throw new NoResultFromDBException();
            }
            return user;
        } catch (Exception e) {
            throw new NoResultFromDBException();
        }
    }

    /**
     * User registration
     * @param user user
     * @throws Exception
     */
    @Override
    public void registerUser(User user) throws Exception {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = daoFactory.createUserDao(connection)) {
            user.setRole(User.Roles.CLIENT.toString());
            userDao.create(user);
        } catch (Exception e) {
            logger.error(LogMessages.USER_REGISTRATION_ERROR);
            throw new Exception();
        }
    }

    /**
     * Driver registration
     * @param driver driver
     * @param car car
     * @throws SQLException
     */
    @Override
    public void registerDriver(User driver, Car car) throws SQLException {
        driver.setRole(User.Roles.DRIVER.toString());
        car.setCarState(Car.CarStates.FREE.toString());
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection);
             UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            connection.setAutoCommit(false);
            userDao.create(driver);
            User newDriver = userDao.findByEmail(driver.getEmail());
            car.setDriver(newDriver);
            carDao.create(car);
            connection.commit();
        } catch (Exception e) {
            logger.error(LogMessages.DRIVER_REGISTRATION_ERROR);
            connection.rollback();
        }
    }

    /**
     * Deletes driver by id
     * @param id driver id
     * @throws SQLException
     */
    @Override
    public void deleteDriverById(Integer id) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection);
             UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            connection.setAutoCommit(false);
            carDao.deleteByParameter(TableParameters.CarParam.DRIVER_ID, id.toString());
            userDao.delete(id);
            connection.commit();
        } catch (Exception e) {
            logger.error(LogMessages.DELETE_DRIVER_ERROR);
            connection.rollback();
        }
    }

    /**
     * Deletes user by id
     * @param id user id
     * @throws SQLException
     */
    @Override
    public void deleteUserById(Integer id) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = daoFactory.createUserDao(connection);
             OrderDao orderDao = daoFactory.createOrderDao(connection)) {
            connection.setAutoCommit(false);
            orderDao.deleteAllOrdersByUserId(id);
            userDao.delete(id);
            connection.commit();
        } catch (Exception e) {
            logger.error(LogMessages.DELETE_USER_BY_ID_ERROR);
            connection.rollback();
        }
    }

    /**
     * Finds all users
     * @return
     * @throws NoResultFromDBException
     */
    @Override
    public List<User> findAllUsers() throws NoResultFromDBException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            return userDao.findAll();
        } catch (Exception e) {
            logger.error(LogMessages.FIND_ALL_USERS_ERROR);
            throw new NoResultFromDBException();
        }
    }

    /**
     * Finds all cars
     * @return
     * @throws NoResultFromDBException
     */
    @Override
    public List<Car> findAllCars() throws NoResultFromDBException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection)) {
            return carDao.findAll();
        } catch (Exception e) {
            logger.error(LogMessages.FIND_ALL_CARS_ERROR);
            throw new NoResultFromDBException();
        }
    }

    /**
     * Finds car by number
     * @param number
     * @return
     * @throws NoResultFromDBException
     */
    @Override
    public Car findCarByNumber(String number) throws NoResultFromDBException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection)) {
            return carDao.findByCarNumber(number);
        } catch (Exception e) {
            logger.error(LogMessages.FIND_CAR_BY_NUMBER_ERROR);
            throw new NoResultFromDBException();
        }
    }

}
