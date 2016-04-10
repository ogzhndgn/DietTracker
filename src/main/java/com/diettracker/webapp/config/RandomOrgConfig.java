package com.diettracker.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 10.04.2016.
 */
@Configuration
@PropertySource("classpath:random.org.properties")
public class RandomOrgConfig {
    @Value("${random.org.apiKey}")
    private String apiKey;
    @Value("${random.org.apiUrl}")
    private String apiUrl;
    @Value("${random.org.apiMethod}")
    private String apiMethod;
    @Value("${random.org.count}")
    private int count;
    @Value("${random.org.length}")
    private int length;
    @Value("${random.org.characters}")
    private String characters;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}