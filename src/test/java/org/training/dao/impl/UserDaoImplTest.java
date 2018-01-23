package org.training.dao.impl;

import org.junit.Test;
import org.training.dao.UserDao;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.dao.factory.DaoFactory;
import org.training.entity.full.User;

import java.sql.Connection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDaoImplTest {
    @Test
    public void create() throws Exception {
        User user = mock(User.class);
        when(user.getFirstName()).thenReturn("IvanTest");
        when(user.getLastName()).thenReturn("PolyakovTest");
        when(user.getPhoneNumber()).thenReturn("0961112233");
        when(user.getEmail()).thenReturn("test@test.test");
        when(user.getPassword()).thenReturn("testTEST");
        when(user.getRole()).thenReturn("client");

        Connection connection = ConnectionPoolHolder.getConnection();
        try(UserDao userDao = DaoFactory.getInstance().createUserDao(connection)){
            connection.setAutoCommit(false);
            userDao.create(user);
            assertNotNull(userDao.findByEmail(user.getEmail()));
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }
    }

    @Test
    public void findByEmail() throws Exception {
        String email = "faoksss@outlook.com";
        Connection connection = ConnectionPoolHolder.getConnection();
        try(UserDao userDao = DaoFactory.getInstance().createUserDao(connection)){
            User user = userDao.findByEmail(email);
            assertNotNull(user);
            assertEquals(email, user.getEmail());
        }
    }

}