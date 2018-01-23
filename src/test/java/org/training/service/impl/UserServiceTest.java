package org.training.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.training.entity.full.User;
import org.training.service.ServiceFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserServiceTest {

    UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        userService = ServiceFactory.getInstance().createUserService();
    }

    @Test
    public void findByEmail() throws Exception {
        String email = "faoksss@outlook.com";
        User user = userService.findByEmail(email);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void registerUser() throws Exception {
    }

    @Test
    public void registerDriver() throws Exception {
    }

    @Test
    public void deleteDriverById() throws Exception {
    }

    @Test
    public void deleteUserById() throws Exception {
    }

    @Test
    public void findAllUsers() throws Exception {
    }

    @Test
    public void findAllCars() throws Exception {
    }

    @Test
    public void findCarByNumber() throws Exception {
    }

}