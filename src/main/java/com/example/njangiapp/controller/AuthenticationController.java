package com.example.njangiapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.njangiapp.model.UserSys;
import com.example.njangiapp.service.UserService;

import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("api/v1/authenticate")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    /**
     * Authenticate user
     *
     * @param
     * @return Logged in user
     */
    @RequestMapping(method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSys> getNjangiAccount(){
        UserSys loggedInUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }
}

