package com.diettracker.webapp.service.api;

import com.diettracker.webapp.config.MailTemplatesConfig;
import com.diettracker.webapp.config.MailgunConfig;
import com.diettracker.webapp.model.PasswordRecovery;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataMultiPart;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    @Autowired
    MailTemplatesConfig mailTemplatesConfig;
    private final Logger logger = LogManager.getLogger(MailSenderService.class);

    public void sendForgotPasswordMail(String email, PasswordRecovery passwordRecovery, String nameSurname) {
        if (StringUtils.isBlank(nameSurname)) {
            nameSurname = "Üyemiz";
        }
        String hash = passwordRecovery.getHash();
        String url = StringUtils.replace(mailgunConfig.getPasswordRecoveryUrl(), "{$HASH}", hash);
        String forgotPasswordMailTemplate = mailTemplatesConfig.getForgotPasswordTemplate();
        String forgotPasswordMailBody = StringUtils.replace(forgotPasswordMailTemplate, "{$URL}", url);
        forgotPasswordMailBody = StringUtils.replace(forgotPasswordMailBody, "{$NAMESURNAME}", nameSurname);
        String response = this.send(email, forgotPasswordMailBody);
        logger.info("Mail sent to " + email + " for hash " + hash + " the response is: " + response);
    }

    private String send(String email, String mailBody) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", mailgunConfig.getApiKey()));
        WebResource webResource = client.resource(mailgunConfig.getUrl());
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        formDataMultiPart.field("from", mailgunConfig.getFromName() + " " + mailgunConfig.getFromAddress());
        formDataMultiPart.field("to", email);
        formDataMultiPart.field("subject", "Forgot Password");
        formDataMultiPart.field("html", mailBody);
        ClientResponse clientResponse = webResource.type(MediaType.MULTIPART_FORM_DATA_VALUE).post(ClientResponse.class, formDataMultiPart);
        return this.logResponse(clientResponse);
    }

    private String logResponse(ClientResponse clientResponse) {
        try {
            InputStream inputStream = clientResponse.getEntityInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            reader.close();
            return out.toString();
        } catch (IOException e) {
            return "CAN_NOT_GET_RESPONSE";
        }
    }
}