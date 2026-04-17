package com.example.hotelapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Hotel creation request")
public record CreateHotelRequest(

    @NotBlank
    @Schema(example = "DoubleTree by Hilton Minsk")
    String name,

    @Schema(example = "Luxury hotel located in the city center with panoramic views")
    String description,

    @NotBlank
    @Schema(example = "Hilton")
    String brand,

    @NotNull
    @Valid
    @Schema(description = "Hotel address details")
    AddressDto address,

    @NotNull
    @Valid
    @Schema(description = "Contact information")
    ContactsDto contacts,

    @NotNull
    @Valid
    @Schema(description = "Check-in and check-out time")
    ArrivalTimeDto arrivalTime
    ){}
