package org.training.dao.impl;

import org.junit.Test;
import org.training.dao.AddressDao;
import org.training.dao.connectionPool.ConnectionPoolHolder;
import org.training.dao.factory.DaoFactory;
import org.training.entity.full.Address;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressDaoImplTest {
    @Test
    public void create() throws Exception {
        String city = "KievTest";
        String street = "PotapovaTest";
        String number = "111";

        Address address = mock(Address.class);
        when(address.getCity()).thenReturn(city);
        when(address.getStreet()).thenReturn(street);
        when(address.getNumber()).thenReturn(number);

        Connection connection = ConnectionPoolHolder.getConnection();
        try(AddressDao addressDao = DaoFactory.getInstance().createAddressDao(connection)){
            connection.setAutoCommit(false);
            addressDao.create(address);
            assertNotNull(addressDao.findAddressByStreetAndNumber(street, number));
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }

    }

    @Test
    public void findAddressByStreet() throws Exception {
        String street = "Булгакова";
        String number = "3";
        Connection connection = ConnectionPoolHolder.getConnection();
        try(AddressDao addressDao = DaoFactory.getInstance().createAddressDao(connection)){
            connection.setAutoCommit(false);
            Address address = addressDao.findAddressByStreetAndNumber(street, number);
            assertNotNull(address);
            assertTrue(address.getStreet().equals(street)
                    & address.getNumber().equals(number));
            connection.rollback();
        }catch (Exception e){
            connection.rollback();
        }
    }

}