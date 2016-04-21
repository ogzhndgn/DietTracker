package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.PasswordRecoveryDao;
import com.diettracker.webapp.enums.PasswordRecoveryStatus;
import com.diettracker.webapp.exception.impl.HashNotActiveException;
import com.diettracker.webapp.exception.impl.HashNotFoundException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.PasswordRecovery;
import com.diettracker.webapp.service.spec.PasswordRecoveryService;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 10.04.2016.
 */
@Service
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {
    public static final int EXPIRY_TIME_IN_DAYS = 3;
    @Autowired
    PasswordRecoveryDao passwordRecoveryDao;
    private final Logger logger = LogManager.getLogger(PasswordRecoveryServiceImpl.class);

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

    @Override
    public PasswordRecovery getByHash(String hash) throws ServiceException {
        try {
            PasswordRecovery passwordRecovery = passwordRecoveryDao.getByHash(hash);
            if (passwordRecovery == null) {
                throw new HashNotFoundException();
            }
            if (passwordRecovery.getStatus() != PasswordRecoveryStatus.ACTIVE) {
                throw new HashNotActiveException();
            }
            Timestamp now = new Timestamp(new Date().getTime());
            if (!(now.after(passwordRecovery.getCreatedTime()) && now.before(passwordRecovery.getExpirationTime()))) {
                throw new HashNotActiveException();
            }
            return passwordRecovery;
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public PasswordRecovery getActiveHash(int userId) throws UnexpectedErrorException, HashNotFoundException {
        Timestamp now = new Timestamp(new Date().getTime());
        try {
            List<PasswordRecovery> passwordRecoveryList = passwordRecoveryDao.get(userId, String.valueOf(PasswordRecoveryStatus.ACTIVE), now);
            if (passwordRecoveryList.isEmpty()) {
                throw new HashNotFoundException();
            }
            if (passwordRecoveryList.size() > 1) {
                logger.info(passwordRecoveryList.size() + " active hash found for userId #" + userId);
            }
            return passwordRecoveryList.get(0);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public boolean updateAfterUse(int id) {
        Timestamp now = new Timestamp(new Date().getTime());
        boolean result = false;
        try {
            int updatedRowCount = passwordRecoveryDao.update(id, now, String.valueOf(PasswordRecoveryStatus.USED));
            result = updatedRowCount > 0;
        } catch (DAOException ignored) {
        }
        return result;
    }
}