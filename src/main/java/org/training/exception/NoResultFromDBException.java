package org.training.exception;

import org.training.constant.messages.ExceptionMessages;
import org.training.util.properties.BundleManager;


/**
 * Throw this exception when there are no result from data base found
 */
public class NoResultFromDBException extends Exception {

    @Override
    public String getMessage() {
        return BundleManager.INSTANCE.getString(ExceptionMessages.NO_RESULT_FROM_DB);
    }
}
