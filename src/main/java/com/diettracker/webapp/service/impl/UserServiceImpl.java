package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.UserDao;
import com.diettracker.webapp.exception.impl.*;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.security.HashService;
import com.diettracker.webapp.service.spec.UserService;
import com.diettracker.webapp.service.util.FormValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User editUserInfo(int id, String name, String password, String confirmPassword) throws ServiceException {
        User user = this.getById(id);
        if (this.isPasswordUpdate(password, confirmPassword)) {
            this.changePassword(id, password);
        }
        this.updateUser(name, id);
        return this.getById(id);
    }

    private void updateUser(String name, int userId) throws ServiceException {

        int updatedRowCount;
        try {
            updatedRowCount = userDao.update(userId, name);
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

    private User getByEmail(String email) throws ServiceException {
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