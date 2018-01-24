package org.training.entity.lazy;

import org.training.constant.db.TableName;
import org.training.constant.db.TableParameters;
import org.training.constant.messages.LogMessages;
import org.training.dao.UserDao;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.entity.Lazy;
import org.training.entity.full.*;

import java.sql.Connection;

public class CarLazy extends Car implements Lazy {

    @Override
    public User getDriver() throws Exception {
        if (super.getDriver() == null) {
            Connection connection = ConnectionPoolHolder.getConnection();
            try (UserDao userDao = daoFactory.createUserDao(connection)) {
                Integer userId = userDao.findForeignKeyInTable(TableName.CAR_TABLE, super.getId().toString(),
                        TableParameters.CarParam.DRIVER_ID);
                return userDao.findById(userId);
            } catch (Exception e) {
                logger.error(e.getMessage() + " " + LogMessages.GET_DRIVER_FROM_CAR_LAZY_ERROR);
                throw new RuntimeException();
            }
        }
        return null;
    }
}
