package com.event.controller;

import com.event.exception.NoResourceAvailableException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.dto.request.AuthRequestDto;
import com.event.dto.request.UserRequestDto;
import com.event.dto.response.AuthResponseDto;
import com.event.service.AuthenticationService;

import java.nio.file.AccessDeniedException;


@RestController
public class AuthenticationController
{
	private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody UserRequestDto request)
	{
		logger.info("Incoming request to register");
		return ResponseEntity.ok(authService.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto request)
	{
		logger.info("Incoming request to login");
		return ResponseEntity.ok(authService.authenticate(request));
	}

	//used so we can spring security 403 forbidden
	@GetMapping("/error")
	public ResponseEntity<?> handle403Exception()
	{
		throw new NoResourceAvailableException("Access Denied!! You do not have permission to access this resource");
	}
}
