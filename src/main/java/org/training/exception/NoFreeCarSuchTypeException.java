package org.training.exception;


import org.training.constant.messages.ExceptionMessages;
import org.training.util.properties.BundleManager;


/**
 * Throw this exception when no free car any type found
 */
public class NoFreeCarSuchTypeException extends Exception {

    @Override
    public String getMessage() {
        return BundleManager.INSTANCE.getString(ExceptionMessages.NO_FREE_CAR_EXCEPTION);
    }
}
