package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.PasswordRecoveryDao;
import com.diettracker.webapp.enums.PasswordRecoveryStatus;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.PasswordRecovery;
import com.diettracker.webapp.service.spec.PasswordRecoveryService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 10.04.2016.
 */
@Service
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {
    public static final int EXPIRY_TIME_IN_DAYS = 3;
    @Autowired
    PasswordRecoveryDao passwordRecoveryDao;


    @Override
    public PasswordRecovery add(int userId, String token, String hash) throws UnexpectedErrorException {
        Timestamp createdTime = new Timestamp(new Date().getTime());
        Date expiry = DateUtils.addDays(new Date(), EXPIRY_TIME_IN_DAYS);
        Timestamp expirationTime = new Timestamp(expiry.getTime());
        try {
            return passwordRecoveryDao.add(userId, token, hash, createdTime, expirationTime, String.valueOf(PasswordRecoveryStatus.ACTIVE));
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }
}
