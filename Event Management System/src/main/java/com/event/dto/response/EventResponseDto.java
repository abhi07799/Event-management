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
public class EventResponseDto
{
    private long id;

    private String title;

    private String description;

    private LocalDate eventDate;

    private String venue;

    private boolean isActive;

    private String bookingStatus;
}
