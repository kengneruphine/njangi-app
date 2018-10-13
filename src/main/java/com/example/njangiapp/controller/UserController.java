package com.example.njangiapp.controller;


import com.example.njangiapp.model.UserSys;
import com.example.njangiapp.model.Role;
import com.example.njangiapp.model.UserDTO;
import com.example.njangiapp.service.RoleService;
import com.example.njangiapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserSys>> getUsers(@RequestParam(value = "username", required = false) String username,
                                                           @RequestParam(value = "active", required = false) String isActive){

        Collection<UserSys> users = new ArrayList<>();
        if (username != null) {
            UserSys user = userService.findByUserName(username);
            users.add(user);

        } else if (isActive != null) {
            if (isActive.compareToIgnoreCase("true") == 0) {
                Collection<UserSys> activeUsers = userService.findByIsActive(true);
                users.addAll(activeUsers);

            } else if (isActive.compareToIgnoreCase("false") == 0){
                Collection<UserSys> deActivatedUsers = userService.findByIsActive(false);
                users.addAll(deActivatedUsers);

            }

        } else {
            Collection<UserSys> allUser = userService.findAll();
            users.addAll(allUser);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get user with given user id.
     *
     * @param userId
     * @return User object or 404 if user is not found
     */
    @RequestMapping(value="/{id}",
            method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSys> getUserById(@PathVariable("id") int userId){

        UserSys user = userService.findById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Create new user.
     *
     * @param user
     * @return User object (created user object)
     */
    @RequestMapping(method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSys> createUser(@RequestBody UserDTO user){
        int count = 0;
        List<Role> roles = new ArrayList<>();
        while(user.getRoleIds().size() > count){
            roles.add(roleService.findById(user.getRoleIds().get(count++)));
        }
        UserSys newUser = new UserSys(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail(),
                user.getPassword(), user.getPhoneNumber(),user.isActive(), roles );
        newUser = userService.create(newUser);
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Update user's information.
     *
     * @param userId
     * @return User object (updated user object)
     */
    @RequestMapping(value="/{userId}",
            method=RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSys> updateUser(@RequestBody UserDTO user){
        int count = 0;
        List<Role> roles = new ArrayList<>();
        while(user.getRoleIds().size() > count){
            roles.add(roleService.findById(user.getRoleIds().get(count++)));
        }
        UserSys updateUser = userService.findById(user.getId());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhoneNumber(user.getPhoneNumber());
        updateUser.setActive(user.isActive());
        updateUser.setRoles(roles);
        updateUser = userService.update(updateUser);

        if (updateUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    /**
     * Delete user's information
     *
     * @param userId
     * @return HTTP status 204, .
     */
    @RequestMapping(value="/{userId}",
            method=RequestMethod.DELETE)
    public ResponseEntity<UserSys> deactivateUser(@PathVariable("userId") int userId){

        userService.deactivate(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

