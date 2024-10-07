package com.event.service;

import com.event.dto.request.EventRequestDto;
import com.event.dto.response.EventResponseDto;
import com.event.exception.NoResourceAvailableException;
import com.event.model.EventModel;
import com.event.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService
{
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private ModelMapper mapper;

    public List<EventResponseDto> allEvents()
    {
        logger.info("Getting all events");
        List<EventModel> events = eventRepo.findAll();

        List<EventResponseDto> eventResponseDto = events.stream().map(event -> mapper.map(event, EventResponseDto.class)).toList();

        if (eventResponseDto.isEmpty())
        {
            logger.info("No events found");
            throw new NoResourceAvailableException("No events found");
        }

        return eventResponseDto;
    }

    public EventResponseDto getEventById(long eventId)
    {
        logger.info("Getting event with id {}", eventId);
        Optional<EventModel> eventOptional = eventRepo.findById(eventId);

        if (eventOptional.isEmpty())
        {
            logger.info("No event found for id {}", eventId);
            throw new NoResourceAvailableException("No event found with id " + eventId);
        }

        return mapper.map(eventOptional.get(), EventResponseDto.class);
    }

    public EventResponseDto createEvent(EventRequestDto eventRequestDto)
    {
        logger.info("Creating event {}", eventRequestDto);
        EventModel eventModel = mapper.map(eventRequestDto, EventModel.class);
        eventModel.setActive(true);
        EventModel savedEvent = eventRepo.save(eventModel);
        return mapper.map(savedEvent, EventResponseDto.class);
    }

    public EventResponseDto deleteEvent(long eventId)
    {
        logger.info("Deleting event {}", eventId);
        Optional<EventModel> eventOptional = eventRepo.findById(eventId);
        if (eventOptional.isEmpty())
        {
            logger.info("No event found with id {}", eventId);
            throw new NoResourceAvailableException("No event found with id " + eventId);
        }

        EventModel event = eventOptional.get();
        event.setActive(false);
        return mapper.map(eventRepo.save(event), EventResponseDto.class);
    }


}
