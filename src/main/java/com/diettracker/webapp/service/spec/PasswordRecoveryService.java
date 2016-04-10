package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.model.PasswordRecovery;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 10.04.2016.
 */
public interface PasswordRecoveryService {
    PasswordRecovery add(int userId, String token, String hash) throws UnexpectedErrorException;
}
