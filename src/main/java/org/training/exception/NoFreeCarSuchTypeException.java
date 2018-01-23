package org.training.exception;


import org.training.constant.messages.ExceptionMessages;
import org.training.util.properties.BundleManager;

public class NoFreeCarSuchTypeException extends Exception {

    @Override
    public String getMessage() {
        return BundleManager.INSTANCE.getString(ExceptionMessages.NO_FREE_CAR_EXCEPTION);
    }
}
