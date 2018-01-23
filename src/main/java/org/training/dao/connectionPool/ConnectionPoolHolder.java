package org.training.dao.connectionPool;

import org.apache.commons.dbcp.BasicDataSource;
import org.training.constant.propertyKeys.Config;
import org.training.util.properties.PropertyManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection pool holder
 */
public class ConnectionPoolHolder {

    /**
     * @see DataSource
     */
    private static volatile DataSource dataSource;

    /**
     * Singleton template implementation
     * @return DataSource
     */
    private static DataSource getDataSource(){
        if(dataSource == null){
            synchronized (ConnectionPoolHolder.class){
                if(dataSource == null){
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(PropertyManager.CONFIG.getString(Config.DB_URL));
                    ds.setUsername(PropertyManager.CONFIG.getString(Config.DB_USER));
                    ds.setPassword(PropertyManager.CONFIG.getString(Config.DB_PASSWORD));
                    ds.setMinIdle(Integer.valueOf(PropertyManager.CONFIG.getString(Config.DB_MIN_IDLE)));
                    ds.setMaxIdle(Integer.valueOf(PropertyManager.CONFIG.getString(Config.DB_MAX_IDLE)));
                    ds.setMaxOpenPreparedStatements(Integer.valueOf(PropertyManager.CONFIG.getString(Config.DB_MAX_OPEN_PS)));
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

    /**
     *
     * @return connection from data source
     * @see Connection
     */
    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
