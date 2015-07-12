package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 5.7.2015
 */
public class PasswordCanNotChangedException extends ServiceException {
    public PasswordCanNotChangedException() {
        super(ApiErrorCode.ERR_PASSWORD_CAN_NOT_CHANGED);
    }
}
