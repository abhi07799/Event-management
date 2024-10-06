package com.event.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.dto.request.AuthRequestDto;
import com.event.dto.request.UserRequestDto;
import com.event.dto.response.AuthResponseDto;
import com.event.service.AuthenticationService;


@RestController
public class AuthenticationController
{
	@Autowired
	private AuthenticationService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody UserRequestDto request)
	{
		return ResponseEntity.ok(authService.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto request)
	{
		return ResponseEntity.ok(authService.authenticate(request));
	}
	
}
