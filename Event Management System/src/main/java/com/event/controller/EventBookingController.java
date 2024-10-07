package com.event.controller;

import com.event.service.EventBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventBookingController
{

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private EventBookingService eventBookingService;

    @GetMapping("public/tet")
    public String test()
    {
        return "Jai Bajrang Bali";
    }

    @PostMapping("user/registerUserForEvent/{userId}/event/{eventId}")
    public ResponseEntity<?> registerUserForEvent(@PathVariable Long userId, @PathVariable Long eventId)
    {
        logger.info("Incoming request for Registering user id : {} for event id : {}", userId ,eventId);
        return new ResponseEntity<>(eventBookingService.registerUserForEvent(userId,eventId), HttpStatus.OK);
    }
}
