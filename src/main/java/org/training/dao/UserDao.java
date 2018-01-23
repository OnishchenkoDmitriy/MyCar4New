package org.training.dao;

import org.training.entity.full.User;
import org.training.exception.NoResultFromDBException;

public interface UserDao extends Dao<User, Integer> {
    User findByEmail(String email) throws NoResultFromDBException;
}
