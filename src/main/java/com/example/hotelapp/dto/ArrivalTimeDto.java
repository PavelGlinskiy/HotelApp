package com.example.hotelapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Schema(description = "Check-in and check-out time")
public record ArrivalTimeDto(

        @JsonFormat(pattern = "HH:mm")
        @Schema(example = "14:00")
        @NotNull
        LocalTime checkIn,

        @JsonFormat(pattern = "HH:mm")
        @Schema(example = "12:00")
        @NotNull
        LocalTime checkOut
) {
}
