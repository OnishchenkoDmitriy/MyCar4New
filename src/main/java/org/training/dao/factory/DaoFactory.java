package org.training.dao.factory;


import org.training.dao.*;

import java.sql.Connection;

/**
 * Abstract dao factory
 */
public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    public abstract UserDao createUserDao(Connection connection);
    public abstract CarDao createCarDao(Connection connection);
    public abstract AddressDao createAddressDao(Connection connection);
    public abstract OrderDao createOrderDao(Connection connection);
    public abstract DiscountDao createDiscountDao(Connection connection);

    /**
     * @return DaoFactory
     */
    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory == null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }



}
