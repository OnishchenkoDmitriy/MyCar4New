package org.training.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.training.dao.factory.DaoFactory;

public interface Lazy {

    Logger logger = LogManager.getLogger(Lazy.class);
    DaoFactory daoFactory = DaoFactory.getInstance();

}
