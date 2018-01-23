package org.training.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.training.exception.EntryAlreadyExistException;
import org.training.exception.NoResultFromDBException;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, ID> extends AutoCloseable{

    /**
     * @see Logger
     */
    Logger logger = LogManager.getLogger(Dao.class);

    /**
     * Creates entry in data source
     * @param entity
     * @throws SQLException
     * @throws EntryAlreadyExistException
     */
    void create(T entity) throws Exception;

    /**
     * Finds entry from data source by id
     * @param id
     * @return lazy entity
     * @throws NoResultFromDBException
     */
    T findById(ID id) throws NoResultFromDBException;

    /**
     * Finds all entry from data source
     * @return result list
     */
    List<T> findAll() throws NoResultFromDBException;

    /**
     * Deletes entry from data source by id
     * @param id
     */
    void delete(ID id);

    /**
     * Deletes entry from data source by parameter
     * @param parameterName
     * @param parameterValue
     */
    void deleteByParameter(String parameterName, String parameterValue);

    /**
     * Finds foreign key in table
     * @param tableName name of table
     * @param entityId entity id
     * @param idName id name
     * @return foreign key
     */
    Integer findForeignKeyInTable(String tableName, String entityId, String idName) throws SQLException;
}
