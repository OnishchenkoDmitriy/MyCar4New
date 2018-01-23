package org.training.entity.full;

import org.training.entity.Entity;
import org.training.entity.EntityBuilder;
import org.training.entity.lazy.OrderLazy;

import java.sql.SQLException;

public class Order implements Entity<Integer> {

    private Integer id;
    private Integer price;
    private User client;
    private User driver;
    private Car car;
    private Address departureAddress;
    private Address arrivalAddress;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public User getClient() throws Exception {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getDriver() throws Exception {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Car getCar() throws Exception {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Address getDepartureAddress() throws Exception {
        return departureAddress;
    }

    public void setDepartureAddress(Address departureAddress) {
        this.departureAddress = departureAddress;
    }

    public Address getArrivalAddress() throws Exception {
        return arrivalAddress;
    }

    public void setArrivalAddress(Address arrivalAddress) {
        this.arrivalAddress = arrivalAddress;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", client=" + client +
                ", driver=" + driver +
                ", car=" + car +
                ", departureAddress=" + departureAddress +
                ", arrivalAddress=" + arrivalAddress +
                '}';
    }

    public static final class OrderBuilder implements EntityBuilder<Order> {
        private Integer id;
        private Integer price;
        private User client;
        private User driver;
        private Car car;
        private Address departureAddress;
        private Address arrivalAddress;

        public OrderBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setPrice(Integer price) {
            this.price = price;
            return this;
        }

        public OrderBuilder setClient(User client) {
            this.client = client;
            return this;
        }

        public OrderBuilder setDriver(User driver) {
            this.driver = driver;
            return this;
        }

        public OrderBuilder setCar(Car car) {
            this.car = car;
            return this;
        }

        public OrderBuilder setDepartureAddress(Address departureAddress) {
            this.departureAddress = departureAddress;
            return this;
        }

        public OrderBuilder setArrivalAddress(Address arrivalAddress) {
            this.arrivalAddress = arrivalAddress;
            return this;
        }

        public Order build(){
            Order order = new Order();
            order.setId(id);
            order.setPrice(price);
            order.setCar(car);
            order.setDriver(driver);
            order.setClient(client);
            order.setDepartureAddress(departureAddress);
            order.setArrivalAddress(arrivalAddress);
            return order;
        }

        public OrderLazy buildLazy(){
            OrderLazy order = new OrderLazy();
            order.setId(id);
            order.setPrice(price);
            return order;
        }
    }
}
