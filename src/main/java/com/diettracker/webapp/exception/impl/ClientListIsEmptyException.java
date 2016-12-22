package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.enums.ApiErrorCode;
import com.diettracker.webapp.exception.spec.ServiceException;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 22.12.2016.
 */
public class ClientListIsEmptyException extends ServiceException {

    public ClientListIsEmptyException() {
        super(ApiErrorCode.ERR_CLIENTLIST_IS_EMPTY);

    }
}