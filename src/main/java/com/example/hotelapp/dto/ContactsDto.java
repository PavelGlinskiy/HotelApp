package com.example.hotelapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Hotel contact information")
public record ContactsDto(

        @NotBlank
        @Schema(example = "+375 17 309-80-00")
        String phone,

        @NotBlank
        @Email
        @Schema(example = "info@hilton.com")
        String email
) {}
