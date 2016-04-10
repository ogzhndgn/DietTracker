package com.diettracker.webapp.service.api;

import com.diettracker.webapp.config.MailgunConfig;
import com.diettracker.webapp.model.PasswordRecovery;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 7.04.2016.
 */
@Component
public class MailSenderService {
    @Autowired
    MailgunConfig mailgunConfig;

    public void sendForgotPasswordMail(String email, PasswordRecovery passwordRecovery) throws IOException {
        String forgotPasswordMailBody = "Hahaha şifreni mi unuttun? " + passwordRecovery.toString();
        this.send(email, forgotPasswordMailBody);
    }

    private void send(String email, String mailBody) throws IOException {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", mailgunConfig.getApiKey()));
        WebResource webResource = client.resource(mailgunConfig.getUrl());
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", mailgunConfig.getFromName() + " " + mailgunConfig.getFromAddress());
        formData.add("to", "dogan_oguzhan@hotmail.com");
        formData.add("subject", "Forgot Password");
        formData.add("text", mailBody);
        ClientResponse clientResponse = webResource.type(String.valueOf(MediaType.APPLICATION_FORM_URLENCODED)).post(ClientResponse.class, formData);
        InputStream inputStream = clientResponse.getEntityInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        System.out.println(out.toString());   //Prints the string content read from input stream
        reader.close();
    }
}