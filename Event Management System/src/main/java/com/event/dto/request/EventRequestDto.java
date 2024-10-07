package com.event.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDto
{
    @NotNull(message = "Title can not be null")
    @NotEmpty(message = "Title can not be empty")
    private String title;

    @NotNull(message = "Description can not be null")
    @NotEmpty(message = "Description can not be empty")
    private String description;

    @NotEmpty(message = "Date can not be empty")
    @NotNull(message = "Date can not be null")
    private LocalDate eventDate;

    @NotEmpty(message = "Venue can not be empty")
    @NotNull(message = "Venue can not be null")
    private String venue;
}
