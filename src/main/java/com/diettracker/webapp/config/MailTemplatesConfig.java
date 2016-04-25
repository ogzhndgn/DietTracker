package com.diettracker.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 26.04.2016.
 */
@Configuration
@PropertySource("classpath:mail.templates.properties")
public class MailTemplatesConfig {
    @Value("${forgotpassword.template}")
    private String forgotPasswordTemplate;

    public String getForgotPasswordTemplate() {
        return forgotPasswordTemplate;
    }

    public void setForgotPasswordTemplate(String forgotPasswordTemplate) {
        this.forgotPasswordTemplate = forgotPasswordTemplate;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}