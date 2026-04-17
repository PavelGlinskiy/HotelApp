package com.example.hotelapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Full hotel information")
public record HotelFullResponse(

    @Schema(example = "1")
    Long id,

    @Schema(example = "DoubleTree by Hilton Minsk")
    String name,

    @Schema(example = "Luxury hotel located in Minsk city center")
    String description,

    @Schema(example = "Hilton")
    String brand,

    @Schema(description = "Hotel address details")
    AddressDto address,

    @Schema(description = "Contact information")
    ContactsDto contacts,

    @Schema(description = "Check-in and check-out time")
    ArrivalTimeDto arrivalTime,

    @Schema(
            description = "List of hotel amenities",
            example = "[\"Free WiFi\", \"Free parking\", \"Fitness center\"]"
    )
    List<String> amenities
){}
