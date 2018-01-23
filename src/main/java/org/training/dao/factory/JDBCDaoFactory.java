package org.training.dao.factory;

import org.training.dao.*;
import org.training.dao.impl.*;

import java.sql.Connection;

import static org.training.constant.db.TableName.*;

/**
 * JDBC Dao factory
 * @see UserDaoImpl
 * @see CarDaoImpl
 * @see OrderDaoImpl
 * @see AddressDaoImpl
 * @see DiscountDaoImpl
 */
public class JDBCDaoFactory extends DaoFactory {

    /**
     *
     * @param connection connection
     * @return UserDaoImpl
     */
    @Override
    public UserDao createUserDao(Connection connection) {
        return new UserDaoImpl(USER_TABLE, connection);
    }

    /**
     *
     * @param connection connection
     * @return CarDaoImpl
     */
    @Override
    public CarDao createCarDao(Connection connection) {
        return new CarDaoImpl(CAR_TABLE, connection);
    }

    /**
     *
     * @param connection connection
     * @return AddressDaoImpl
     */
    @Override
    public AddressDao createAddressDao(Connection connection) {
        return new AddressDaoImpl(ADDRESS_TABLE, connection);
    }

    /**
     *
     * @param connection connection
     * @return OrderDaoImpl
     */
    @Override
    public OrderDao createOrderDao(Connection connection) {
        return new OrderDaoImpl(ORDER_TABLE, connection);
    }

    /**
     *
     * @param connection connection
     * @return DiscountDaoImpl
     */
    @Override
    public DiscountDao createDiscountDao(Connection connection) {
        return new DiscountDaoImpl(DISCOUNT_TABLE, connection);
    }


}
