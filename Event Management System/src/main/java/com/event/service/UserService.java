package com.event.service;

import com.event.controller.AuthenticationController;
import com.event.dto.response.UserResponseDto;
import com.event.exception.NoResourceAvailableException;
import com.event.model.UserModel;
import com.event.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper mapper;

    public List<UserResponseDto> allUsers()
    {
        logger.info("Getting all users");
        List<UserModel> users = userRepo.findAll();

        if (users.isEmpty())
        {
            logger.info("No users found");
            throw new NoResourceAvailableException("No Users Found");
        }

        return users.stream().map(user -> mapper.map(user, UserResponseDto.class)).toList();
    }

    public UserResponseDto getUserById(long userId)
    {
        logger.info("Getting user by id: {}", userId);
        Optional<UserModel> user = userRepo.findById(userId);
        if(user.isEmpty())
        {
            logger.info("User not found with id: {}", userId);
            throw new NoResourceAvailableException("No User Found with ID: " + userId);
        }
        return mapper.map(user.get(), UserResponseDto.class);
    }
}
