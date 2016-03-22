package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.User;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.6.2015
 */
public interface UserService {
    User registerUser(String email, String password, String confirmPassword) throws ServiceException;

    User loginUser(String email, String password) throws ServiceException;

    User editUserInfo(int id, String name, String password, String confirmPassword) throws ServiceException;

    User getByEmail(String email) throws ServiceException;
}
