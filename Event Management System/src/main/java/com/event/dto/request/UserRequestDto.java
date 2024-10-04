package com.event.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto
{	
	private String userFullName;
	
	private String userMail;
	
	private String userMobile;
	
	private String userPassword;
	
	private String userAddress;

	private String userRole;
}
