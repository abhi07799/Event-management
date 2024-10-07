package com.event.service;

import com.event.controller.AuthenticationController;
import com.event.exception.NoResourceAvailableException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.event.dto.request.AuthRequestDto;
import com.event.dto.request.UserRequestDto;
import com.event.dto.response.AuthResponseDto;
import com.event.model.UserModel;
import com.event.repository.UserRepository;
import com.event.security.JwtAuthService;


@Service
public class AuthenticationService
{
	private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private JwtAuthService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ModelMapper mapper;
	
	public AuthResponseDto register(UserRequestDto requestDto)
	{
		logger.info("Registering new user and generating the token");
		UserModel request = mapper.map(requestDto, UserModel.class);
		
		request.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
		
		UserModel user  = repo.save(request);
		
		String token = jwtService.generateToken(user);
		
		return new AuthResponseDto(token);
	}
	
	public AuthResponseDto authenticate(AuthRequestDto requestDto)
	{
		logger.info("Authenticating user credentials and generating the token");
		UserModel request = mapper.map(requestDto, UserModel.class);
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		UserModel user =repo.findByUserMail(request.getUsername()).orElseThrow(()->new NoResourceAvailableException("No User found for this email: "+requestDto.getUserMail()));
		
		String token = jwtService.generateToken(user);
		
		return new AuthResponseDto(token);
	}
}
