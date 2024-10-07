package com.event.controller;

import com.event.dto.request.EventRequestDto;
import com.event.service.EventService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/")
public class EventController
{

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private EventService eventService;

    @GetMapping("public/event")
    public String test()
    {
        return "Jai Bajrang Bali";
    }

    @PostMapping("createEvent")
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventRequestDto eventRequestDto)
    {
        logger.info("Incoming request to create an event");
        return new ResponseEntity<>(eventService.createEvent(eventRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("allEvents")
    public ResponseEntity<?> getAllEvents()
    {
        logger.info("Incoming request to get all events");
        return new ResponseEntity<>(eventService.allEvents(),HttpStatus.OK);
    }

    @GetMapping("getEvent/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable Long eventId)
    {
        logger.info("Incoming request to get an event by id : {}",eventId);
        return new ResponseEntity<>(eventService.getEventById(eventId),HttpStatus.OK);
    }

    @DeleteMapping("removeEvent/{eventId}")
    public ResponseEntity<?> removeEvent(@PathVariable Long eventId)
    {
        logger.info("Incoming request to remove an event by id : {}",eventId);
        return new ResponseEntity<>(eventService.deleteEvent(eventId),HttpStatus.OK);
    }
}
