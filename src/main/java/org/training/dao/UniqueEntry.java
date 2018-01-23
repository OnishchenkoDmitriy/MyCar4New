package org.training.dao;

public interface UniqueEntry<T> {
    /**
     * Checks if entry is already exist in data source
     * @param entity entity
     * @return true or false
     */
    boolean noEntryInDB(T entity);
}
