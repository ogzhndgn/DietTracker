package com.diettracker.webapp.service.security;

import org.springframework.stereotype.Component;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
@Component
public class HashService {

    public String hashPassword(String password, String salt) {
        return password;
    }

    public String getPasswordSalt() {
        return "SALT";
    }
}
