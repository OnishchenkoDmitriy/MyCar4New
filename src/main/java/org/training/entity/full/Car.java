package org.training.entity.full;

import org.training.entity.Entity;
import org.training.entity.EntityBuilder;
import org.training.entity.lazy.CarLazy;

import java.util.List;

public class Car implements Entity<Integer> {

    /**
     * Car states
     */
    public enum CarStates{FREE, BUSY};

    /**
     * Car types
     */
    public enum CarTypes{CLASSIC, PREMIUM};

    private Integer id;
    private String number;
    private String brand;
    private String model;
    private String color;

    private String state;
    private String type;
    private User driver;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarState() {
        return state;
    }

    public void setCarState(String state) {
        this.state = state;
    }

    public String getCarType() {
        return type;
    }

    public void setCarType(String type) {
        this.type = type;
    }

    public User getDriver() throws Exception {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!number.equals(car.number)) return false;
        if (!brand.equals(car.brand)) return false;
        if (!model.equals(car.model)) return false;
        return color.equals(car.color);
    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", state=" + state +
                ", type=" + type +
                ", driver=" + driver +
                '}';
    }

    /**
     * Car builder
     */
    public static final class CarBuilder implements EntityBuilder<Car>{
        private Integer id;
        private String number;
        private String brand;
        private String model;
        private String color;
        private String state;
        private String type;
        private User driver;

        public CarBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public CarBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public CarBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setCarState(String state) {
            this.state = state;
            return this;
        }

        public CarBuilder setCarType(String type) {
            this.type = type;
            return this;
        }

        public CarBuilder setDriver(User driver) {
            this.driver = driver;
            return this;
        }

        public Car build(){
            Car car = new Car();
            car.setId(id);
            car.setNumber(number);
            car.setBrand(brand);
            car.setModel(model);
            car.setColor(color);
            car.setCarState(state);
            car.setCarType(type);
            car.setDriver(driver);
            return car;
        }

        public CarLazy buildLazy(){
            CarLazy car = new CarLazy();
            car.setId(id);
            car.setNumber(number);
            car.setBrand(brand);
            car.setModel(model);
            car.setColor(color);
            car.setCarState(state);
            car.setCarType(type);
            return car;
        }
    }
}
