package com.diettracker.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 7.04.2016.
 */
@Configuration
@PropertySource("classpath:mailgun.properties")
public class MailgunConfig {

    @Value("${mailgun.apiKey}")
    private String apiKey;
    @Value("${mailgun.url}")
    private String url;
    @Value("${mailgun.fromName}")
    private String fromName;
    @Value("${mailgun.fromAddress}")
    private String fromAddress;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}