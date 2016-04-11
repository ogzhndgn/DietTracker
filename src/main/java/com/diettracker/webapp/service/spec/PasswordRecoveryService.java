package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.impl.HashNotActiveException;
import com.diettracker.webapp.exception.impl.HashNotFoundException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.PasswordRecovery;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 10.04.2016.
 */
public interface PasswordRecoveryService {
    PasswordRecovery add(int userId, String token, String hash) throws UnexpectedErrorException;

    PasswordRecovery getByHash(String hash) throws UnexpectedErrorException, HashNotFoundException, HashNotActiveException, ServiceException;
}
