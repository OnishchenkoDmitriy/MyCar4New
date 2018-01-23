package org.training.entity.full;

import org.training.entity.Entity;

public class Address implements Entity<Integer> {

    private Integer id;
    private String city;
    /**
     * street name
     */
    private String street;
    /**
     * street number
     */
    private String number;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }


    /**
     * Address builder
     */
    public static final class AddressBuilder{
        private Integer id;
        private String city;
        private String street;
        private String number;

        public AddressBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Address build(){
            Address address = new Address();
            address.setId(id);
            address.setCity(city);
            address.setStreet(street);
            address.setNumber(number);
            return address;
        }

    }
}
