package org.training.dao.impl;

import org.training.constant.db.TableName;
import org.training.constant.db.TableParameters;
import org.training.constant.messages.LogMessages;
import org.training.dao.AbstractDao;
import org.training.dao.CarDao;
import org.training.dao.UniqueEntry;
import org.training.dao.util.QueryContainer;
import org.training.entity.full.Car;
import org.training.entity.lazy.CarLazy;
import org.training.exception.EntryAlreadyExistException;
import org.training.exception.NoResultFromDBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static org.training.constant.db.TableParameters.CarParam.*;

public class CarDaoImpl extends AbstractDao<Car> implements CarDao, UniqueEntry<Car> {


    public CarDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected Car extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(TableParameters.ID);
        String number = resultSet.getString(NUMBER);
        String brand = resultSet.getString(BRAND);
        String model = resultSet.getString(MODEL);
        String color = resultSet.getString(COLOR);
        String state = resultSet.getString(STATE);
        String type = resultSet.getString(TYPE);

        return new CarLazy.CarBuilder()
                .setId(id)
                .setNumber(number)
                .setBrand(brand)
                .setModel(model)
                .setColor(color)
                .setCarState(state)
                .setCarType(type)
                .buildLazy();
    }

    @Override
    public void create(Car car) throws Exception {
        if(noEntryInDB(car)){
            try (PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO Car(number, brand, model, color, state, type, driver_id)" +
                            " VALUES (?,?,?,?,?,?,?)")) {
                ps.setString(1, car.getNumber());
                ps.setString(2, car.getBrand());
                ps.setString(3, car.getModel());
                ps.setString(4, car.getColor());
                ps.setString(5, car.getCarState());
                ps.setString(6, car.getCarType());
                ps.setInt(7, car.getDriver().getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                logger.error(e.getMessage() + " " + LogMessages.CREATE_CAR_ERROR);
            }
        }else {
            throw new EntryAlreadyExistException();
        }
    }

    @Override
    public boolean noEntryInDB(Car car) {
        try {
            return (Objects.isNull(findByCarNumber(car.getNumber())));
        } catch (NoResultFromDBException e) {
            return true;
        }
    }

    @Override
    public void updateCarState(Integer carId, String carState) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE " + TableName.CAR_TABLE + " SET state = ? WHERE id = ?")) {
            ps.setString(1, carState);
            ps.setInt(2, carId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage() + " " + LogMessages.UPDATE_CAR_STATE_ERROR);
            throw new SQLException();
        }
    }

    @Override
    public Car findByCarNumber(String number) throws NoResultFromDBException {
        return findOneByParameter(TableParameters.CarParam.NUMBER, number);
    }

    @Override
    public Car findFreeCarByType(String carType) throws NoResultFromDBException {
        String query = QueryContainer.INSTANCE.findFreeCarByType(carType);
        return findOneByQuery(query);
    }

}
