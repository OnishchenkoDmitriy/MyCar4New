package org.training.exception;

import org.training.constant.messages.ExceptionMessages;
import org.training.util.properties.BundleManager;


/**
 * Throw this exception when entry already exist in data base
 */
public class EntryAlreadyExistException extends Exception{

    @Override
    public String getMessage() {
        return BundleManager.INSTANCE.getString(ExceptionMessages.ENTRY_ALREADY_EXIST);
    }
}
