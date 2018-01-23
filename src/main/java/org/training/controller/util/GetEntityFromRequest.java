package org.training.controller.util;

import org.training.constant.jsp.RequestParameters;
import org.training.entity.full.Address;
import org.training.entity.full.Car;
import org.training.entity.full.User;

import javax.servlet.http.HttpServletRequest;

public class GetEntityFromRequest {
    public static User getUserFromRequest(HttpServletRequest request) {
        String email = request.getParameter(RequestParameters.EMAIL);
        String firstName = request.getParameter(RequestParameters.FIRST_NAME);
        String lastName = request.getParameter(RequestParameters.LAST_NAME);
        String phoneNumber = request.getParameter(RequestParameters.PHONE_NUMBER);
        String password = request.getParameter(RequestParameters.PASSWORD);

        return new User.UserBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .setRole(String.valueOf(User.Roles.CLIENT))
                .build();
    }

    public static Car getCarFromRequest(HttpServletRequest request){
        String carNumber = request.getParameter(RequestParameters.CAR_NUMBER);
        String carBrand = request.getParameter(RequestParameters.CAR_BRAND);
        String carModel = request.getParameter(RequestParameters.CAR_MODEL);
        String carColor = request.getParameter(RequestParameters.CAR_COLOR);
        String carType = request.getParameter(RequestParameters.CAR_TYPE);

        return new Car.CarBuilder()
                .setNumber(carNumber)
                .setBrand(carBrand)
                .setModel(carModel)
                .setColor(carColor)
                .setCarType(carType)
                .build();
    }


    public static Address getDepartureAddressFromRequest(HttpServletRequest request) {
        String city = request.getParameter(RequestParameters.CITY);
        String departureStreet = request.getParameter(RequestParameters.DEPARTURE_STREET);
        String departureNumber = request.getParameter(RequestParameters.DEPARTURE_NUMBER);

        return new Address.AddressBuilder()
                .setCity(city)
                .setStreet(departureStreet)
                .setNumber(departureNumber)
                .build();
    }

    public static Address getArrivalAddressFromRequest(HttpServletRequest request) {
        String city = request.getParameter(RequestParameters.CITY);
        String arrivalStreet = request.getParameter(RequestParameters.ARRIVAL_STREET);
        String arrivalNumber = request.getParameter(RequestParameters.ARRIVAL_NUMBER);

        return new Address.AddressBuilder()
                .setCity(city)
                .setStreet(arrivalStreet)
                .setNumber(arrivalNumber)
                .build();
    }
}
