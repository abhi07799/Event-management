package com.event.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventBookingResponseDto
{
    private String userName;
    private String userMail;
    private String eventTitle;
    private LocalDate eventDate;
    private String bookingStatus;
}
