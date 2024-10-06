package com.event.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto
{
	@NotNull(message = "Mail Id can not be null")
	@NotEmpty(message = "Main Id can not be empty")
	private String userMail;

	@NotNull(message = "Password can not be null")
	@NotEmpty(message = "Password can not be empty")
	private String userPassword;
}
