package com.diettracker.webapp.service.spec;

import java.io.IOException;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 7.04.2016.
 */
public interface MailSenderService {

    void send(String email, String mailBody) throws IOException;
}
