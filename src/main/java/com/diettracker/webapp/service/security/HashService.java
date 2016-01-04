package com.diettracker.webapp.service.security;

import com.diettracker.webapp.exception.impl.PasswordHashException;
import com.diettracker.webapp.exception.impl.SaltGeneratingException;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
@Component
public class HashService {

    public String hashPassword(String password, String salt) throws PasswordHashException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            return this.byteToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordHashException();
        }
    }

    public String getPasswordSalt() throws SaltGeneratingException {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return this.byteToHex(salt);
        } catch (NoSuchAlgorithmException e) {
            throw new SaltGeneratingException();
        }
    }

    private String byteToHex(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : byteArray) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
