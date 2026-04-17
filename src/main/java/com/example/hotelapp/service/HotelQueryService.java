package com.example.hotelapp.service;

import com.example.hotelapp.dto.HotelFullResponse;
import com.example.hotelapp.dto.HotelSearchRequest;
import com.example.hotelapp.dto.HotelShortResponse;
import com.example.hotelapp.entity.Hotel;
import com.example.hotelapp.exception.BadRequestException;
import com.example.hotelapp.exception.NotFoundException;
import com.example.hotelapp.mapper.HotelMapper;
import com.example.hotelapp.repository.HotelRepository;
import com.example.hotelapp.specification.HotelSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HotelQueryService {

    private final HotelRepository repository;
    private final HotelMapper mapper;

    public List<HotelShortResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toShort)
                .toList();
    }

    public HotelFullResponse getById(Long id) {
        Hotel hotel = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel with id=" + id + " not found"));

        return mapper.toFull(hotel);
    }

    public Page<HotelShortResponse> search(HotelSearchRequest request) {

        Specification<Hotel> spec = HotelSpecification.filter(
                request.name(),
                request.brand(),
                request.city(),
                request.country(),
                request.getAmenityList()
        );

        Pageable pageable = buildPageable(
                request.getPageOrDefault(),
                request.getSizeOrDefault(),
                request.getSortByOrDefault(),
                request.getDirectionOrDefault()
        );

        return repository.findAll(spec, pageable)
                .map(mapper::toShort);
    }

    private Pageable buildPageable(int page, int size, String sortBy, String direction) {

        Set<String> allowed = Set.of("id", "name", "brand");

        if (!allowed.contains(sortBy)) {
            throw new BadRequestException("Invalid sort field: " + sortBy);
        }

        Sort.Direction dir = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(page, size, Sort.by(dir, sortBy));
    }
}
