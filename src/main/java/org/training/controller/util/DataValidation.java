package org.training.controller.util;

import org.training.constant.jsp.JSPPages;
import org.training.constant.jsp.RequestAttributes;
import org.training.constant.jsp.RequestParameters;
import org.training.constant.messages.ValidationMessages;
import org.training.constant.regex.RegexContainer;
import org.training.exception.RightDataFormat;
import org.training.util.properties.BundleManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Util class for data validation
 * @see RegexContainer
 */
public class DataValidation {

    /**
     * @see BundleManager
     */
    private static BundleManager rbm = BundleManager.INSTANCE;

    /**
     * Checks all data for driver.
     * If all data are correct throws RightDataFormat
     * else return way to driver registration page
     * @param request
     * @return way to driver registration page
     * @throws RightDataFormat if all data are correct
     */
    public static String validateDriverData(HttpServletRequest request) throws RightDataFormat {
        return userDataValidation(request, JSPPages.DRIVER_REG_PAGE);
    }

    /**
     * Checks all data for client.
     * If all data are correct throws RightDataFormat
     * else return way to client registration page
     * @param request
     * @return way to user registration page
     * @throws RightDataFormat if all data are correct
     */
    public static String validateClientData(HttpServletRequest request) throws RightDataFormat {
        return userDataValidation(request, JSPPages.USER_REG_PAGE);
    }

    /**
     * Checks all data for client.
     * If all data are correct throws RightDataFormat
     * else return way to user registration page
     * @param request
     * @return way to user registration page
     * @throws RightDataFormat if all data are correct
     */
    private static String userDataValidation(HttpServletRequest request, String page) throws RightDataFormat {
        String email = request.getParameter(RequestParameters.EMAIL);
        String firstName = request.getParameter(RequestParameters.FIRST_NAME);
        String lastName = request.getParameter(RequestParameters.LAST_NAME);
        String phoneNumber = request.getParameter(RequestParameters.PHONE_NUMBER);
        String password = request.getParameter(RequestParameters.PASSWORD);

        if (email == null || !email.matches(rbm.getString(RegexContainer.REGEX_EMAIL))) {
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, DataValidation.rbm.getString(ValidationMessages.WRONG_EMAIL));
            return page;
        } else if (firstName == null
                || !firstName.matches(rbm.getString(RegexContainer.REGEX_NAME))) {
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, DataValidation.rbm.getString(ValidationMessages.WRONG_NAME));
            return page;
        } else if (lastName == null
                ||  !lastName.matches(rbm.getString(RegexContainer.REGEX_LAST_NAME))) {
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, DataValidation.rbm.getString(ValidationMessages.WRONG_LAST_NAME));
            return page;
        } else if (phoneNumber == null
                || !phoneNumber.matches(rbm.getString(RegexContainer.REGEX_PHONE_NUMBER))) {
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, DataValidation.rbm.getString(ValidationMessages.WRONG_PHONE_NUMBER));
            return page;
        } else if (password == null
                || !password.matches(rbm.getString(RegexContainer.REGEX_PASSWORD))) {
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, DataValidation.rbm.getString(ValidationMessages.WRONG_PASSWORD));
            return page;
        } else if (!password.equals(request.getParameter(RequestParameters.PASSWORD2))) {
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, DataValidation.rbm.getString(ValidationMessages.WRONG_PASSWORD2));
            return page;
        } else {
            throw new RightDataFormat();
        }
    }

    /**
     * Checks all data for car.
     * If all data are correct throws RightDataFormat
     * else return way to driver registration page
     * @param request
     * @return way to driver registration page
     * @throws RightDataFormat if all data are correct
     */
    public static String carDataValidation(HttpServletRequest request) throws RightDataFormat {
        String carNumber = request.getParameter(RequestParameters.CAR_NUMBER);
        String carBrand = request.getParameter(RequestParameters.CAR_BRAND);
        String carModel = request.getParameter(RequestParameters.CAR_MODEL);

        if(carNumber == null || !carNumber.matches(rbm.getString(RegexContainer.CAR_NUMBER_REGEX))){
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, rbm.getString(ValidationMessages.WRONG_CAR_NUMBER));
            return JSPPages.DRIVER_REG_PAGE;
        }else if(carBrand == null || !carBrand.matches(rbm.getString(RegexContainer.CAR_BRAND_REGEX))){
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, rbm.getString(ValidationMessages.WRONG_CAR_BRAND));
            return JSPPages.DRIVER_REG_PAGE;
        }else if(carModel == null || !carBrand.matches(rbm.getString(RegexContainer.CAR_MODEL_REGEX))){
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, rbm.getString(ValidationMessages.WRONG_CAR_MODEL));
            return JSPPages.DRIVER_REG_PAGE;
        }else {
            throw new RightDataFormat();
        }
    }

    /**
     * Checks all data for address.
     * If all data are correct throws RightDataFormat
     * else return way to driver registration page
     * @param request
     * @return way to home page depends on user role
     * @throws RightDataFormat if all data are correct
     */
    public static String addressDataValidation(HttpServletRequest request) throws RightDataFormat {
        String city = request.getParameter(RequestParameters.CITY);
        String departureStreet = request.getParameter(RequestParameters.DEPARTURE_STREET);
        String departureNumber = request.getParameter(RequestParameters.DEPARTURE_NUMBER);
        String arrivalStreet = request.getParameter(RequestParameters.ARRIVAL_STREET);
        String arrivalNumber = request.getParameter(RequestParameters.ARRIVAL_NUMBER);

        if (city == null
                || !city.matches(rbm.getString(RegexContainer.CITY_REGEX))){
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, rbm.getString(ValidationMessages.WRONG_CITY));
            return OtherUtil.getUserIndexPage(request);
        }else if (departureStreet == null
                || !departureStreet.matches(rbm.getString(RegexContainer.STREET_REGEX))){
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, rbm.getString(ValidationMessages.WRONG_DEPARTURE_STREET));
            return OtherUtil.getUserIndexPage(request);
        }else if (departureNumber == null
                || !departureNumber.matches(rbm.getString(RegexContainer.STREET_NUMBER_REGEX))){
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, rbm.getString(ValidationMessages.WRONG_DEPARTURE_NUMBER));
            return OtherUtil.getUserIndexPage(request);
        }else if (arrivalStreet == null
                || !arrivalStreet.matches(rbm.getString(RegexContainer.STREET_REGEX))){
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, rbm.getString(ValidationMessages.WRONG_ARRIVAL_STREET));
            return OtherUtil.getUserIndexPage(request);
        }else if (arrivalNumber == null
                || !arrivalNumber.matches(rbm.getString(RegexContainer.STREET_NUMBER_REGEX))){
            request.setAttribute(RequestAttributes.ERROR_DATA_MESSAGE, rbm.getString(ValidationMessages.WRONG_ARRIVAL_NUMBER));
            return OtherUtil.getUserIndexPage(request);
        }else {
            throw new RightDataFormat();
        }
    }
}
