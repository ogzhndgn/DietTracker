package com.diettracker.webapp.test;

import com.diettracker.webapp.model.User;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 5.04.2016.
 */
public class RestApiClient {
    private static final String REST_API_URL = "http://localhost:8080/diettracker/restapi";
    public static final String NEW_USER_EMAIL = "testperson_" + System.currentTimeMillis() + "@gmail.com";
    public static final String NEW_USER_NAME = "Name " + System.currentTimeMillis();

    private static void listAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_API_URL + "/users", List.class);
        for (LinkedHashMap<String, Object> userMap : usersMap) {
            System.out.println("Id: " + userMap.get("id") + " Name: " + userMap.get("name") + " Email: " + userMap.get("email"));
        }
    }

    private static void getUser() {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_API_URL + "/user/dogan_oguzhan@hotmail.com/", User.class);
        System.out.println("Id: " + user.getId() + " Name: " + user.getName() + " Email: " + user.getEmail());
    }

    private static void createUser() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setEmail(NEW_USER_EMAIL);
        user.setPassword("asdASD123!");
        URI uri = restTemplate.postForLocation(REST_API_URL + "/user/", user, User.class);
        System.out.println("Location : " + uri.toASCIIString());
    }

    private static void updateUser() {
        String mail = NEW_USER_EMAIL;
        User user = new User();
        user.setEmail(mail);
        user.setName(NEW_USER_NAME);
        user.setPassword("asdASD123!");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_API_URL + "/user/" + mail + "/", user);
        System.out.println(user);
    }

    private static void updateUserWithoutMail() {
        String mail = NEW_USER_EMAIL;
        User user = new User();
        user.setEmail(mail);
        user.setName(NEW_USER_NAME);
        user.setPassword("asdASD123!");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_API_URL + "/user", user);
        System.out.println(user);
    }

    private static void deleteUser() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_API_URL + "/user/dogan_oguzhan@hotmail.com/");
    }

    private static void deleteAll() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_API_URL + "/users");
    }

    public static void main(String[] args) {
        getUser();
        System.out.println("--------------------------");
        createUser();
        System.out.println("--------------------------");
//        updateUser();
        updateUserWithoutMail();
        System.out.println("--------------------------");
        listAllUsers();
        System.out.println("--------------------------");
        deleteUser();
        System.out.println("--------------------------");
        deleteAll();
        System.out.println("--------------------------");
    }
}