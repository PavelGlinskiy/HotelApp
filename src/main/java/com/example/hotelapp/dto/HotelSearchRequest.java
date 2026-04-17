package com.example.hotelapp.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.List;

public record HotelSearchRequest(

        String name,
        String brand,
        String city,
        String country,
        String amenities,

        @PositiveOrZero
        Integer page,

        @Positive
        Integer size,
        String sortBy,

        @Pattern(regexp = "asc|desc", message = "direction must be 'asc' or 'desc'")
        String direction

) {

    public int getPageOrDefault() {
        return page != null ? page : 0;
    }

    public int getSizeOrDefault() {
        return size != null ? size : 10;
    }

    public String getSortByOrDefault() {
        return sortBy != null ? sortBy : "id";
    }

    public String getDirectionOrDefault() {
        return direction != null ? direction : "asc";
    }

    public List<String> getAmenityList() {
        if (amenities == null) return null;
        return Arrays.stream(amenities.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();
    }
}
