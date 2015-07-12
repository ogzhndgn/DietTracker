package com.diettracker.webapp.service.security;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
@Component
public class IdGenerator {

    public String generateGUID() {
        return String.valueOf(UUID.randomUUID());
    }
}
