package com.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto
{
	private long id;
	
	private String userFullName;
	
	private String userMail;
	
	private String userMobile;
	
	private String userPassword;
	
	private String userAddress;

	private String userRole;
}
