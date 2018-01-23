package org.training.service;

import org.training.service.impl.OrderServiceImpl;
import org.training.service.impl.UserServiceImpl;

public class ServiceFactory {

    private ServiceFactory() {
    }

    private static class ServiceFactoryHolder {
        private static ServiceFactory instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.instance;
    }


    public UserServiceImpl createUserService(){
        return UserServiceImpl.getInstance();
    }

    public OrderServiceImpl createOrderService(){
        return OrderServiceImpl.getInstance();
    }

}
