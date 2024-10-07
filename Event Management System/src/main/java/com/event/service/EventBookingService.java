package com.event.service;

import com.event.dto.response.EventBookingResponseDto;
import com.event.dto.response.EventResponseDto;
import com.event.exception.NoResourceAvailableException;
import com.event.model.EventModel;
import com.event.model.UserModel;
import com.event.repository.EventRepository;
import com.event.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventBookingService
{

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper mapper;

    public EventBookingResponseDto registerUserForEvent(long userId, long eventId)
    {
        logger.info("Registering user with Id: {} for event {}", userId, eventId);

        if(!eventRepo.existsById(eventId))
        {
            logger.info("No event found with id {}", eventId);
            throw new NoResourceAvailableException("No event found with id " + eventId);
        }

        if(!userRepo.existsById(userId))
        {
            logger.info("No user found with id {}", userId);
            throw new NoResourceAvailableException("No user found with id " + userId);
        }

        UserModel user = userRepo.findById(userId).get();
        EventModel event = eventRepo.findById(eventId).get();

        user.setEvent(event);
        UserModel savedUser = userRepo.save(user);

        event.setBookingStatus("Confirmed");
        eventRepo.save(event);

        return EventBookingResponseDto.builder()
                .userName(savedUser.getUserFullName())
                .userMail(savedUser.getUserMail())
                .eventTitle(event.getTitle())
                .eventDate(event.getEventDate())
                .bookingStatus("Confirmed")
                .build();
    }
}
