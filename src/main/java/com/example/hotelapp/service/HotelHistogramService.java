package com.example.hotelapp.service;

import com.example.hotelapp.dto.HistogramRow;
import com.example.hotelapp.entity.HistogramType;
import com.example.hotelapp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelHistogramService {

    private final HotelRepository repository;

    public Map<String, Long> histogram(HistogramType type) {

        List<HistogramRow> rows = switch (type) {
            case BRAND -> repository.countByBrand();
            case CITY -> repository.countByCity();
            case COUNTRY -> repository.countByCountry();
            case AMENITIES -> repository.countByAmenities();
        };

        return rows.stream()
                .filter(r -> r.getKey() != null)
                .collect(Collectors.toMap(
                        HistogramRow::getKey,
                        HistogramRow::getCount
                ));
    }
}
