package org.training.dao;

import org.training.entity.full.Address;
import org.training.exception.NoResultFromDBException;

public interface AddressDao extends Dao<Address, Integer> {

    /**
     * Finds address by street name and street number
     * @param street name of street
     * @param number number of street
     * @return address
     * @throws NoResultFromDBException
     */
    Address findAddressByStreetAndNumber(String street, String number) throws NoResultFromDBException;
}
