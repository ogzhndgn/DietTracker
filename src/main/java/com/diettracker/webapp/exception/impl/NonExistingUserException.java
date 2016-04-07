package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.enums.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 21.6.2015
 */
public class NonExistingUserException extends ServiceException {

    public NonExistingUserException() {
        super(ApiErrorCode.ERR_USER_NOT_EXISTS);
    }
}
