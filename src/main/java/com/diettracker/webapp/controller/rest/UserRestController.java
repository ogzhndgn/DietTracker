package com.diettracker.webapp.controller.rest;

import com.diettracker.webapp.exception.impl.NonExistingUserException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.04.2016.
 */
@RestController
@RequestMapping(value = "/restapi")
public class UserRestController {
    @Autowired
    UserService userService;

    /**
     * LIST ALL USERS
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * LIST USER BY EMAIL
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user/{email}/", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("email") String email) {
        try {
            return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);
        } catch (NonExistingUserException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UnexpectedErrorException ue) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * CREATE NEW USER
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
        String email = user.getEmail();
        try {
            userService.getByEmail(email);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NonExistingUserException ne) {
            try {
                userService.registerUser(user.getEmail(), user.getPassword(), user.getPassword());
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setLocation(uriComponentsBuilder.path("/restapi/user/{email}/").buildAndExpand(user.getEmail()).toUri());
                return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
            } catch (ServiceException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException se) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * UPDATE USER NAME AND PASSWORD
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user/{email}/", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("email") String email, @RequestBody User user) {
        try {
            User userToBeUpdated = userService.getByEmail(email);
            userToBeUpdated = userService.editUserInfo(userToBeUpdated.getId(), user.getName(), user.getPassword(), user.getPassword());
            return new ResponseEntity<>(userToBeUpdated, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * UPDATE USER NAME AND PASSWORD
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUserWithoutMail(@RequestBody User user) {
        try {
            User userToBeUpdated = userService.getByEmail(user.getEmail());
            userToBeUpdated = userService.editUserInfo(userToBeUpdated.getId(), user.getName(), user.getPassword(), user.getPassword());
            return new ResponseEntity<>(userToBeUpdated, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE USER BY EMAIL
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user/{email}/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("email") String email) {
        try {
            User user = userService.getByEmail(email);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}