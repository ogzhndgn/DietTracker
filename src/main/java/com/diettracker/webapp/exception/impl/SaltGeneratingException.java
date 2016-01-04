package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 04.01.2016
 */
public class SaltGeneratingException extends ServiceException {

    public SaltGeneratingException() {
        super(ApiErrorCode.ERR_SALT_GENERATING);
    }
}
