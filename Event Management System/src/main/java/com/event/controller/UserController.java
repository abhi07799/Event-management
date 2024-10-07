package com.event.controller;

import com.event.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;

    @GetMapping("public/user")
    public String test()
    {
        return "Jai Bajrang Bali";
    }

    @GetMapping("admin/allUsers")
    public ResponseEntity<?> getAllUsers()
    {
        logger.info("Incoming request for getting all users");
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("user/getUserById/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId)
    {
        logger.info("Incoming request for getting user by id {}", userId);
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

}
