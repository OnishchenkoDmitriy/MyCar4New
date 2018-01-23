package org.training.util.logUtil;

import org.training.entity.full.User;

/**
 * Message builder for logger
 */
public enum LogMessageBuilder {
    INSTANCE;

    public String logInInfo(User user){
        return "User " + user.getFirstName() + user.getLastName() + " login system.";
    }

    public String logOutInfo(String userName){
        return "User " + userName + " logout system.";
    }

    public String driverRegistrationInfo(String userName) {
        return "New user " + userName + " registered as driver.";
    }

    public String clientRegistrationInfo(String userName) {
        return "New user " + userName + " registered as client.";
    }

    public String makeOrderInfo(String userName) {
        return "User " + userName + " made an order";
    }

    public String confirmOrderInfo(String userName) {
        return "User " + userName + " confirmed an order";
    }

    public String cancelOrderInfo(String userName) {
        return "User " + userName + " canceled an order";
    }

    public String unknownCommandError(String commandName) {
        return "Unknown command. Command name: " + commandName;
    }

    public String deleteFromTableError(String tableName) {
        return "Delete error from " + tableName + " error.";
    }

    public String userHasNoDiscountsYet(User user) {
        return "User: " + user.getFirstName() + " "
                + user.getLastName() + " has no discounts yet.";
    }
}
