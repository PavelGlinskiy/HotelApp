package com.example.hotelapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Schema(description = "Hotel address information")
public record AddressDto(

        @Positive
        @Schema(example = "9")
        Integer houseNumber,

        @NotBlank
        @Schema(example = "Pobediteley Avenue")
        String street,

        @NotBlank
        @Schema(example = "Minsk")
        String city,

        @NotBlank
        @Schema(example = "Belarus")
        String country,

        @NotBlank
        @Schema(example = "220004")
        String postCode
) {}
