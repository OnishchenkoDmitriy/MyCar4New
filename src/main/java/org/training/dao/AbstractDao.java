package org.training.dao;

import org.training.constant.messages.LogMessages;
import org.training.dao.util.QueryContainer;
import org.training.entity.Entity;
import org.training.exception.NoResultFromDBException;
import org.training.util.logUtil.LogMessageBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for DAO
 * @param <T>
 */
public abstract class AbstractDao<T extends Entity<Integer>> implements Dao<T, Integer> {

    /**
     * name of table in data base
     */
    private String tableName;
    /**
     * @see Connection
     */
    public Connection connection;

    public AbstractDao(String tableName, Connection connection) {
        this.tableName = tableName;
        this.connection = connection;
    }

    @Override
    public T findById(Integer id) throws NoResultFromDBException {
        String query = QueryContainer.INSTANCE.findFromTableById(tableName, id);
        return findOneByQuery(query);
    }

    /**
     * Finds one entry by parameter
     * @param parameterName name of parameter
     * @param parameterValue parameter value
     * @return entity
     * @throws NoResultFromDBException
     */
    protected T findOneByParameter(String parameterName, String parameterValue)
            throws NoResultFromDBException {
        String query = QueryContainer.INSTANCE.findAllFromTableByParameter(tableName,
                parameterName, parameterValue);
        return findOneByQuery(query);
    }

    /**
     * Finds all entry py parameter
     * @param parameterName name of parameter
     * @param parameterValue parameter value
     * @return list of entities
     */
    protected List<T> findAllByParameter(String parameterName, String parameterValue)
            throws NoResultFromDBException {
        String query = QueryContainer.INSTANCE.findAllFromTableByParameter(tableName,
                parameterName, parameterValue);
        return findAllByQuery(query);
    }

    @Override
    public List<T> findAll() throws NoResultFromDBException {
        String query = QueryContainer.INSTANCE.findAllFromTable(tableName);
        return findAllByQuery(query);
    }

    @Override
    public void delete(Integer id) {
        String query = QueryContainer.INSTANCE.deleteFromTableById(tableName, id);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteFromTableError(tableName), e.getMessage());
        }
    }

    /**
     * Deletes entry by parameter
     * @param parameterName name of parameter
     * @param parameterValue parameter value
     */
    public void deleteByParameter(String parameterName, String parameterValue) {
        String query = QueryContainer.INSTANCE.deleteFromTableByParameter(tableName,
                parameterName, parameterValue);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteFromTableError(tableName), e.getMessage());
        }
    }

    @Override
    public Integer findForeignKeyInTable(String tableName, String entityId, String idName) throws SQLException {
        String query = QueryContainer.INSTANCE.findForeignKeyIdFromTable(tableName, entityId, idName);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(idName);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error(LogMessages.FIND_OTHER_ID_FROM_TABLE_ERROR);
            throw new SQLException();
        }
    }

    /**
     * Finds one entry by query
     * @param query query
     * @return entity
     * @throws NoResultFromDBException
     */
    protected T findOneByQuery(String query) throws NoResultFromDBException {
        try (PreparedStatement ps = connection.prepareStatement(query);) {
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return extractFromResultSet(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error(LogMessages.FIND_BY_QUERY_ERROR);
            throw new NoResultFromDBException();
        }
    }

    /**
     * Finds all entries by query
     * @param query query
     * @return list of entities
     */
    protected List<T> findAllByQuery(String query) throws NoResultFromDBException {
        List<T> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(extractFromResultSet(resultSet));
            }
            return resultList;
        } catch (SQLException e) {
            logger.error(LogMessages.FIND_ALL_BY_QYERY_ERROR);
            throw new NoResultFromDBException();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LogMessages.CONNECTION_CLOSE_ERROR);
        }
    }

    /**
     * Executes query
     * @param query query
     * @throws SQLException
     */
    protected void executeQuery(String query) throws SQLException {
        connection.prepareStatement(query).execute();
    }

    /**
     * Extracts entity from result set
     * @param resultSet
     * @return
     * @throws SQLException
     */
    protected abstract T extractFromResultSet(ResultSet resultSet) throws SQLException;
}
