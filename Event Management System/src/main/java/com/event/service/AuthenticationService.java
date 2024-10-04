package com.event.service;

import org.modelmapper.ModelMapper;
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
		UserModel request = mapper.map(requestDto, UserModel.class);
		
		request.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
		
		UserModel user  = repo.save(request);
		
		String token = jwtService.generateToken(user);
		
		return new AuthResponseDto(token);
	}
	
	public AuthResponseDto authenticate(AuthRequestDto requestDto)
	{
		UserModel request = mapper.map(requestDto, UserModel.class);
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		UserModel user =repo.findByUserMail(request.getUsername()).orElseThrow();
		
		String token = jwtService.generateToken(user);
		
		return new AuthResponseDto(token);
	}
}
