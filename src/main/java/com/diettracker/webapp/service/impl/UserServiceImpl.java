package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.UserDao;
import com.diettracker.webapp.exception.impl.*;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.model.wrapper.SqlDate;
import com.diettracker.webapp.service.security.HashService;
import com.diettracker.webapp.service.spec.UserService;
import com.diettracker.webapp.service.util.FormValidator;
import com.diettracker.webapp.service.util.UtilityService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.6.2015
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    FormValidator formValidator;
    @Autowired
    HashService hashService;
    @Autowired
    UtilityService utilityService;
    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User registerUser(String email, String password, String confirmPassword) throws ServiceException {
        this.validateEmail(email);
        this.checkPasswordCompatibility(password, confirmPassword);
        this.existsUser(email);
        return this.addUser(email, password);
    }

    @Override
    public User loginUser(String email, String password) throws ServiceException {
        try {
            User user = this.getByEmail(email);
            String passwordSalt = user.getPasswordSalt();
            String passwordHash = hashService.hashPassword(password, passwordSalt);
            user = userDao.get(email, passwordHash);
            if (user == null || !user.isActive()) {
                throw new NonExistingUserException();
            }
            return user;
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public User editUserInfo(int id, String name, String password, String confirmPassword, String birthDateStr) throws ServiceException {
        User user = this.getById(id);
        if (this.isPasswordUpdate(password, confirmPassword)) {
            this.changePassword(id, password);
        }
        Date birthDate = utilityService.convertDate(birthDateStr);
        this.updateUser(name, id, birthDate);
        return this.getById(id);
    }

    @Override
    public User getByEmail(String email) throws ServiceException {
        User user;
        try {
            user = userDao.get(email);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
        if (user == null) {
            throw new NonExistingUserException();
        }
        return user;
    }

    @Override
    public void deleteUser(String email) {
        logger.info("User: " + email + " will be deleted from the system");
    }

    @Override
    public void deleteAllUsers() {
        logger.info("All users will be deleted...");
    }

    @Override
    public void recoverPassword(int id, String password, String confirmPassword) throws ServiceException {
        User user = this.getById(id);
        if (!this.isPasswordUpdate(password, confirmPassword)) {
            throw new PasswordCanNotBeBlankException();
        }
        this.changePassword(id, password);
    }

    @Override
    public User getDieticiansClient(int dieticianId, int clientId) throws ServiceException {
        User client = this.getById(clientId);
        if (client.getDieticianId() != dieticianId) {
            throw new NotAuthorizedException();
        }
        return client;
    }

    private void updateUser(String name, int userId, Date birthDate) throws ServiceException {
        int updatedRowCount;
        try {
            SqlDate sqlBirthDate = utilityService.convertToSqlDate(birthDate);
            updatedRowCount = userDao.update(userId, name, sqlBirthDate);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
        if (updatedRowCount > 0) {
            return;
        }
        throw new UserCanNotUpdatedException();
    }

    private void changePassword(int id, String password) throws UnexpectedErrorException, PasswordCanNotChangedException, SaltGeneratingException, PasswordHashException {
        String passwordSalt = hashService.getPasswordSalt();
        String passwordHash = hashService.hashPassword(password, passwordSalt);
        int updatedRowCount;
        try {
            updatedRowCount = userDao.updatePassword(id, passwordHash, passwordSalt);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
        if (updatedRowCount > 0) {
            return;
        }
        throw new PasswordCanNotChangedException();
    }

    private boolean isPasswordUpdate(String password, String confirmPassword) throws ServiceException {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        this.checkPasswordCompatibility(password, confirmPassword);
        return true;
    }


    private User addUser(String email, String password) throws UserCanNotCreatedException, SaltGeneratingException, PasswordHashException {
        try {
            String passwordSalt = hashService.getPasswordSalt();
            String passwordHash = hashService.hashPassword(password, passwordSalt);
            return userDao.add(email, passwordHash, passwordSalt);
        } catch (DAOException e) {
            throw new UserCanNotCreatedException();
        }
    }

    private void validateEmail(String email) throws ServiceException {
        if (formValidator.validateEmail(email)) {
            return;
        }
        throw new InvalidEmailException();
    }

    private User getById(int id) throws ServiceException {
        User user;
        try {
            user = userDao.get(id);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
        if (user == null) {
            throw new NonExistingUserException();
        }
        return user;
    }

    private void checkPasswordCompatibility(String password, String confirmPassword) throws ServiceException {
        if (!formValidator.validatePassword(password)) {
            throw new InvalidPasswordFormatException();
        }
        if (!StringUtils.equals(password, confirmPassword)) {
            throw new PasswordDoesntMatchException();
        }
    }

    private void existsUser(String email) throws ServiceException {
        try {
            this.getByEmail(email);
        } catch (NonExistingUserException e) {
            return;
        }
        throw new UserExistsException();
    }
}