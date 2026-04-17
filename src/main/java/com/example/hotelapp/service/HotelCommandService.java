package com.example.hotelapp.service;

import com.example.hotelapp.dto.CreateHotelRequest;
import com.example.hotelapp.dto.HotelShortResponse;
import com.example.hotelapp.entity.Hotel;
import com.example.hotelapp.exception.AmenityAlreadyExistsException;
import com.example.hotelapp.exception.NotFoundException;
import com.example.hotelapp.mapper.HotelMapper;
import com.example.hotelapp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelCommandService {

    private final HotelRepository repository;
    private final HotelMapper mapper;

    public HotelShortResponse create(CreateHotelRequest request) {
        Hotel entity = mapper.toEntity(request);
        return mapper.toShort(repository.save(entity));
    }

    public void addAmenities(Long id, List<String> amenities) {

        Hotel hotel = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel with id=" + id + " not found"));

        Set<String> existing = hotel.getAmenities();

        Set<String> duplicates = amenities.stream()
                .filter(existing::contains)
                .collect(Collectors.toSet());

        if (!duplicates.isEmpty()) {
            throw new AmenityAlreadyExistsException(String.join(",", duplicates));
        }

        existing.addAll(amenities);
        repository.save(hotel);
    }
}
