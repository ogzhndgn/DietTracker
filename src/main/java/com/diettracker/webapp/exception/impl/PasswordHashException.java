package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.enums.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 04.01.2016
 */
public class PasswordHashException extends ServiceException {

    public PasswordHashException() {
        super(ApiErrorCode.ERR_PASSWORD_HASH);
    }
}
