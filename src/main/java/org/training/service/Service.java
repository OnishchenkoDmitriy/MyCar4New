package org.training.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.training.dao.factory.DaoFactory;

public interface Service{
    DaoFactory daoFactory = DaoFactory.getInstance();
    Logger logger = LogManager.getLogger(Service.class);
}
