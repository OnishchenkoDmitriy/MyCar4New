package org.training.dao.impl;

import org.training.constant.db.TableParameters;
import org.training.constant.messages.LogMessages;
import org.training.dao.AbstractDao;
import org.training.dao.UniqueEntry;
import org.training.dao.UserDao;
import org.training.entity.full.User;
import org.training.exception.NoResultFromDBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static org.training.constant.db.TableParameters.UserParam.*;
import static org.training.constant.db.TableParameters.UserParam.PASSWORD;

public class UserDaoImpl extends AbstractDao<User> implements UserDao, UniqueEntry<User> {


    public UserDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    public void create(User user) throws SQLException {
        if(noEntryInDB(user)){
            try (PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO user(firstName, lastName, phoneNumber, email, password, role)" +
                            " VALUES (?,?,?,?,?,?)")) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhoneNumber());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getPassword());
                ps.setString(6, user.getRole());
                ps.executeUpdate();
            } catch (SQLException e) {
                logger.error(LogMessages.CREATE_USER_ERROR, e.getMessage());
                throw new SQLException();
            }
        }
    }

    @Override
    public boolean noEntryInDB(User user) {
        try {
            return (Objects.isNull(findByEmail(user.getEmail())));
        } catch (NoResultFromDBException e) {
            return true;
        }
    }

    @Override
    protected User extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(TableParameters.ID);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String phoneNumber = resultSet.getString(PHONE_NUMBER);
        String email = resultSet.getString(EMAIL);
        String password = resultSet.getString(PASSWORD);
        String role = resultSet.getString(ROLE);
        return new User.UserBuilder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .build();
    }

    @Override
    public User findByEmail(String email) throws NoResultFromDBException {
        return findOneByParameter(TableParameters.UserParam.EMAIL, email);
    }

}
