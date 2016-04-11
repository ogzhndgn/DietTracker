package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.enums.ApiErrorCode;
import com.diettracker.webapp.exception.spec.ServiceException;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 12.04.2016.
 */
public class HashNotActiveException extends ServiceException {

    public HashNotActiveException() {
        super(ApiErrorCode.ERR_HASH_NOT_ACTIVE);
    }
}
