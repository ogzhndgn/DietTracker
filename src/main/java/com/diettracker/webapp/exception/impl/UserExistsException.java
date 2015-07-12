package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 29.6.2015
 */
public class UserExistsException extends ServiceException {
    public UserExistsException() {
        super(ApiErrorCode.ERR_USER_EXISTS);
    }
}
