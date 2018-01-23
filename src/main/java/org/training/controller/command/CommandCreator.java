package org.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.training.constant.command.CommandNames;
import org.training.constant.jsp.JSPPages;
import org.training.controller.command.orderCommand.CancelOrderCommand;
import org.training.controller.command.orderCommand.ConfirmOrderCommand;
import org.training.controller.command.orderCommand.MakeOrderCommand;
import org.training.controller.command.orderCommand.MyOrdersCommand;
import org.training.controller.command.other.AllCarsCommand;
import org.training.controller.command.other.AllUsersCommand;
import org.training.controller.command.other.DeleteUserCommand;
import org.training.controller.command.redirectCommand.*;
import org.training.controller.command.registrationAndAuthorizationCommand.DriverRegistrationCommand;
import org.training.controller.command.registrationAndAuthorizationCommand.LoginCommand;
import org.training.controller.command.registrationAndAuthorizationCommand.LogoutCommand;
import org.training.controller.command.registrationAndAuthorizationCommand.UserRegistrationCommand;
import org.training.service.ServiceFactory;
import org.training.util.logUtil.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.training.constant.command.CommandNames.*;

/**
 * Contains all command in concurrentMap.
 * Do some action depends on concrete command.
 * @see CommandNames
 * @see  Command
 * @see ConcurrentMap
 * @see LogManager
 * @see Logger
 */

public class CommandCreator {
    /**
     * Logger for CommandCreator class
     */
    private static final Logger logger = LogManager.getLogger(CommandCreator.class);
    /**
     *Hash map that contains all command, where: key - command names, value - concrete command.
     */
    private ConcurrentMap<String, Command> commandMap = new ConcurrentHashMap<>();

    private CommandCreator(){

        commandMap.put(LOGIN_COMMAND,
                new LoginCommand(ServiceFactory.getInstance().createUserService()));
        commandMap.put(LOGOUT_COMMAND, new LogoutCommand());
        commandMap.put(USER_REGISTRATION_COMMAND,
                new UserRegistrationCommand(ServiceFactory.getInstance().createUserService()));
        commandMap.put(DRIVER_REGISTRATION_COMMAND,
                new DriverRegistrationCommand(ServiceFactory.getInstance().createUserService()));
        commandMap.put(GET_CAR_COMMAND,
                new MakeOrderCommand(ServiceFactory.getInstance().createOrderService()));
        commandMap.put(CANCEL_ORDER_COMMAND,
                new CancelOrderCommand(ServiceFactory.getInstance().createOrderService()));
        commandMap.put(CONFIRM_ORDER_COMMAND,
                new ConfirmOrderCommand(ServiceFactory.getInstance().createOrderService()));
        commandMap.put(MY_ORDERS_PAGE_COMMAND,
                new MyOrdersCommand(ServiceFactory.getInstance().createOrderService()));

        commandMap.put(USER_REGISTRATION_PAGE_COMMAND, new UserRegPageCommand());
        commandMap.put(DRIVER_REGISTRATION_PAGE_COMMAND, new DriverRegPageCommand());
        commandMap.put(CONTACT_PAGE_COMMAND, new ContactPageCommand());
        commandMap.put(INDEX_PAGE_COMMAND, new IndexPageCommand());
        commandMap.put(PROFILE_PAGE_COMMAND, new ProfilePageCommand());
        commandMap.put(ALL_USERS_COMMAND,
                new AllUsersCommand(ServiceFactory.getInstance().createUserService()));
        commandMap.put(ALL_CARS_COMMAND,
                new AllCarsCommand(ServiceFactory.getInstance().createUserService()));
        commandMap.put(DELETE_USER_COMMAND,
                new DeleteUserCommand(ServiceFactory.getInstance().createUserService()));
    }

    /**
     * Singleton template implementation for CommandCreator
     */
    private static class CommandCreatorHolder {
        private static final CommandCreator instance = new CommandCreator();
    }

    /**
     * @return Command creator
     */
    public static CommandCreator getInstance() {
        return CommandCreatorHolder.instance;
    }

    /**
     * Get concrete command from hash map by key.
     * Do method execute and returns concrete way to jsp {@link JSPPages}.
     * @param commandName name of command
     * @param request request
     * @param response response
     * @return
     * @throws IOException
     */
    public String action(String commandName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Command command = commandMap.get(commandName);
            return command.execute(request, response);
        }catch (NullPointerException e){
            logger.error(LogMessageBuilder.INSTANCE.unknownCommandError(commandName));
            return JSPPages.ERROR_PAGE;
        }
    }
}
