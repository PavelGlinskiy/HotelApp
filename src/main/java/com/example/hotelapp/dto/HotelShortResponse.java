package com.example.hotelapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Short hotel information (list view)")
public record HotelShortResponse(

        @Schema(example = "1")
        Long id,

        @Schema(example = "DoubleTree by Hilton Minsk")
        String name,

        @Schema(example = "Luxury hotel in Minsk with city view")
        String description,

        AddressDto address,

        @Schema(example = "+375 17 309-80-00")
        String phone
) {}
