package org.training.dao.impl;

import org.training.constant.db.TableParameters;
import org.training.constant.messages.LogMessages;
import org.training.dao.AbstractDao;
import org.training.dao.AddressDao;
import org.training.dao.UniqueEntry;
import org.training.dao.util.QueryContainer;
import org.training.entity.full.Address;
import org.training.exception.NoResultFromDBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static org.training.constant.db.TableParameters.AddressParam.CITY;
import static org.training.constant.db.TableParameters.AddressParam.NUMBER;
import static org.training.constant.db.TableParameters.AddressParam.STREET;


public class AddressDaoImpl extends AbstractDao<Address> implements AddressDao, UniqueEntry<Address> {


    public AddressDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected Address extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(TableParameters.ID);
        String city = resultSet.getString(CITY);
        String street = resultSet.getString(STREET);
        String number = resultSet.getString(NUMBER);
        return new Address.AddressBuilder()
                .setId(id)
                .setCity(city)
                .setStreet(street)
                .setNumber(number)
                .build();
    }

    @Override
    public void create(Address address) throws SQLException {
        if(noEntryInDB(address)){
            try (PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO address(city, street, number)" +
                            " VALUES (?,?,?)")) {
                ps.setString(1, address.getCity());
                ps.setString(2, address.getStreet());
                ps.setString(3, address.getNumber());
                ps.executeUpdate();
            } catch (SQLException e) {
                logger.error(LogMessages.CREATE_ADDRESS_ERROR);
                throw new SQLException();
            }
        }
    }

    @Override
    public boolean noEntryInDB(Address address) {
        try {
            return Objects.isNull(findAddressByStreetAndNumber(address.getStreet(), address.getNumber()));
        } catch (NoResultFromDBException e) {
            return true;
        }
    }

    @Override
    public Address findAddressByStreetAndNumber(String street, String number) throws NoResultFromDBException {
        String query = QueryContainer.INSTANCE.findAddressByStreetAndNumber(street, number);
        return findOneByQuery(query);
    }
}
