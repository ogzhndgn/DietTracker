package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.enums.ApiErrorCode;
import com.diettracker.webapp.exception.spec.ServiceException;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 21.04.2016.
 */
public class PasswordCanNotBeBlankException extends ServiceException {
    public PasswordCanNotBeBlankException() {
        super(ApiErrorCode.ERR_PASSWORD_CAN_NOT_BE_BLANK);
    }
}
