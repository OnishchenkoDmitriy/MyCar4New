package org.training.entity.lazy;

import org.training.constant.db.TableName;
import org.training.constant.db.TableParameters;
import org.training.constant.messages.LogMessages;
import org.training.dao.AddressDao;
import org.training.dao.CarDao;
import org.training.dao.UserDao;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.entity.Lazy;
import org.training.entity.full.Address;
import org.training.entity.full.Car;
import org.training.entity.full.Order;
import org.training.entity.full.User;

import java.sql.Connection;

public class OrderLazy extends Order implements Lazy {

    @Override
    public User getDriver() throws Exception {
        if(super.getDriver() == null){
            Connection connection = ConnectionPoolHolder.getConnection();
            try(UserDao userDao = daoFactory.createUserDao(connection)){
                Integer userId = userDao.findForeignKeyInTable(TableName.ORDER_TABLE, super.getId().toString(),
                        TableParameters.OrderParam.DRIVER_ID);
                return userDao.findById(userId);
            } catch (Exception e) {
                logger.error(e.getMessage() + " " + LogMessages.GET_DRIVER_FROM_ORDER_LAZY_ERROR);
                throw new RuntimeException();
            }
        }
        return null;
    }

    @Override
    public User getClient() throws Exception {
        if(super.getClient() == null){
            Connection connection = ConnectionPoolHolder.getConnection();
            try(UserDao userDao = daoFactory.createUserDao(connection)){
                Integer userId = userDao.findForeignKeyInTable(TableName.ORDER_TABLE, super.getId().toString(),
                        TableParameters.OrderParam.CLIENT_ID);
                return userDao.findById(userId);
            } catch (Exception e) {
                logger.error(e.getMessage() + " " + LogMessages.GET_CLIENT_FROM_ORDER_LAZY_ERROR);
                throw new Exception();
            }
        }
        return null;
    }

    @Override
    public Car getCar() throws Exception {
        if(super.getCar() == null){
            Connection connection = ConnectionPoolHolder.getConnection();
            try(CarDao carDao = daoFactory.createCarDao(connection)){
                Integer carId = carDao.findForeignKeyInTable(TableName.ORDER_TABLE, super.getId().toString(),
                        TableParameters.OrderParam.CAR_ID);
                return carDao.findById(carId);
            } catch (Exception e) {
                logger.error(e.getMessage() + " " + LogMessages.GET_CAR_FROM_ORDER_LAZY_ERROR);
                throw new Exception();
            }
        }
        return null;
    }

    @Override
    public Address getArrivalAddress() throws Exception {
        if(super.getArrivalAddress() == null){
            Connection connection = ConnectionPoolHolder.getConnection();
            try(AddressDao addressDao = daoFactory.createAddressDao(connection)){
                Integer addressId = addressDao.findForeignKeyInTable(TableName.ORDER_TABLE, super.getId().toString(),
                        TableParameters.OrderParam.ARRIVAL_ADDRESS_ID);
                return addressDao.findById(addressId);
            } catch (Exception e) {
                logger.error(e.getMessage() + " " + LogMessages.GET_ARRIVAL_ADDRESS_FROM_ORDER_LAZY_ERROR);
                throw new Exception();
            }
        }
        return null;
    }

    @Override
    public Address getDepartureAddress() throws Exception {
        if(super.getDepartureAddress() == null){
            Connection connection = ConnectionPoolHolder.getConnection();
            try(AddressDao addressDao =  daoFactory.createAddressDao(connection)){
                Integer addressId = addressDao.findForeignKeyInTable(TableName.ORDER_TABLE, super.getId().toString(),
                        TableParameters.OrderParam.DEPARTURE_ADDRESS_ID);
                return addressDao.findById(addressId);
            } catch (Exception e) {
                logger.error(e.getMessage() + " " + LogMessages.GET_ARRIVAL_DEPARTURE_FROM_ORDER_LAZY_ERROR);
                throw new Exception();
            }
        }
        return null;
    }
}
