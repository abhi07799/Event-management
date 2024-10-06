package com.event.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
