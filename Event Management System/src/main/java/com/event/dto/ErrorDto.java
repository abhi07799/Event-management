package com.event.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto
{
	private LocalDateTime timestamp;
	private String message;
	private String error;
	private Map<String, String> multiErrors;
	private String path;
	private String exceptionStackTrace;

}
