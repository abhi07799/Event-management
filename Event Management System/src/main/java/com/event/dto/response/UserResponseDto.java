package com.event.dto.response;

import com.event.model.EventModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto
{
	private long id;

	private String userFullName;

	private String userMail;

	private String userMobile;

	private String userPassword;

	private String userAddress;

	private String userRole;

	private LocalDate accountCreatedDate;

	private EventModel event;
}
