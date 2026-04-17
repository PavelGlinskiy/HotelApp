package com.example.hotelapp.controller;

import com.example.hotelapp.dto.CreateHotelRequest;
import com.example.hotelapp.dto.HotelFullResponse;
import com.example.hotelapp.dto.HotelSearchRequest;
import com.example.hotelapp.dto.HotelShortResponse;
import com.example.hotelapp.entity.HistogramType;
import com.example.hotelapp.service.HotelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

@Tag(name = "Hotels", description = "API for managing hotels")
@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;

    @Operation(summary = "Get all hotels")
    @GetMapping("/hotels")
    public List<HotelShortResponse> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get hotel by id")
    @GetMapping("/hotels/{id}")
    public HotelFullResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Search hotels with filters")
    @GetMapping("/search")
    public Page<HotelShortResponse> search(@ModelAttribute HotelSearchRequest request) {
        return service.search(request);
    }

    @Operation(summary = "Create new hotel")
    @PostMapping("/hotels")
    public HotelShortResponse create(@Valid @RequestBody CreateHotelRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Add amenities to hotel")
    @PostMapping("/hotels/{id}/amenities")
    public void addAmenities(@PathVariable Long id,
                             @RequestBody @NotEmpty List<@NotBlank String> amenities) {
        service.addAmenities(id, amenities);
    }

    @Operation(summary = "Get histogram by param")
    @GetMapping("/histogram")
    public Map<String, Long> histogram(@RequestParam HistogramType type) {
        return service.histogram(type);
    }
}
