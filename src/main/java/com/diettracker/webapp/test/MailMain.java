package com.diettracker.webapp.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.http.MediaType;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 3.04.2016.
 */
public class MailMain {
    public static void main(String[] args) throws MessagingException, IOException {
//        Properties props = System.getProperties();
//        props.put("mail.smtps.host", "smtp.mailgun.org");
//        props.put("mail.smtps.auth", "true");
//        Session session = Session.getInstance(props, null);
//        Message msg = new MimeMessage(session);
//        msg.setFrom(new InternetAddress("postmaster@sandboxa65ebf0cee6a49399edb964fc937a284.mailgun.org"));
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dogan_oguzhan@hotmail.com", false));
//        msg.setSubject("Hello");
//        msg.setText("Testing some Mailgun awesomness");
//        msg.setSentDate(new Date());
//        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
//        t.connect("smtp.mailgun.org", "postmaster@sandboxa65ebf0cee6a49399edb964fc937a284.mailgun.org", "6489426");
//        t.sendMessage(msg, msg.getAllRecipients());
//        System.out.println("Response: " + t.getLastServerResponse());
//        t.close();
        ClientResponse clientResponse = SendSimpleMessage();
        InputStream inputStream = clientResponse.getEntityInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        System.out.println(out.toString());   //Prints the string content read from input stream
        reader.close();
//        System.out.println(SendSimpleMessage());
//        Map<String, Object> clientResponseProp = clientResponse.getProperties();
//        for (String s : clientResponseProp.keySet()) {
//            System.out.println(s + " " + clientResponseProp.get(s));
//        }
    }

    public static ClientResponse SendSimpleMessage() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-00e41bc9e8c8b079597caa9a8ac108ef"));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandboxa65ebf0cee6a49399edb964fc937a284.mailgun.org" + "/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Diet Tracker <postmaster@sandboxa65ebf0cee6a49399edb964fc937a284.mailgun.org>");
        formData.add("to", "dogan_oguzhan@hotmail.com");
        formData.add("subject", "Forgot Password");
        formData.add("text", "Haha n00b! Did you forget your password? Kola reeeeeyy");
        return webResource.type(String.valueOf(MediaType.APPLICATION_FORM_URLENCODED)).post(ClientResponse.class, formData);
    }

}