package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.enums.ApiErrorCode;
import com.diettracker.webapp.exception.spec.ServiceException;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 25.12.2016.
 */
public class NotAuthorizedException extends ServiceException {

    public NotAuthorizedException() {
        super(ApiErrorCode.ERR_NOT_AUTHORIZED);
    }
}