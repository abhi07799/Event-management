package com.event.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@NotNull(message = "Full Name can not be null")
	@NotEmpty(message = "Full Name can not be empty")
	private String userFullName;

	@NotNull(message = "Mail Id can not be null")
	@NotEmpty(message = "Mail Id can not be null")
	private String userMail;

	@NotNull(message = "Mobile Number can not be null")
	@NotEmpty(message = "Mobile Number can not be empty")
	private String userMobile;

	@NotNull(message = "Password can not be null")
	@NotEmpty(message = "Password can not be empty")
	private String userPassword;

	@NotNull(message = "Address can not be null")
	@NotEmpty(message = "Address can not be empty")
	private String userAddress;

	@NotNull(message = "Role can not be null")
	@NotEmpty(message = "Role can not be empty")
	@Pattern(regexp = "User|EventAdmin|VenueAdmin|USER|EVENTADMIN|VENUEADMIN", message = "Role must be one of these only User, EventAdmin, VenueAdmin, USER, EVENTADMIN, VENUEADMIN,")
	private String userRole;
}
