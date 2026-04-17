package com.example.hotelapp.service;

import com.example.hotelapp.dto.CreateHotelRequest;
import com.example.hotelapp.dto.HotelFullResponse;
import com.example.hotelapp.dto.HotelSearchRequest;
import com.example.hotelapp.dto.HotelShortResponse;
import com.example.hotelapp.entity.HistogramType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelQueryService queryService;
    private final HotelCommandService commandService;
    private final HotelHistogramService histogramService;

    public List<HotelShortResponse> getAll() {
        return queryService.getAll();
    }

    public HotelFullResponse getById(Long id) {
        return queryService.getById(id);
    }

    public Page<HotelShortResponse> search(HotelSearchRequest request) {
        return queryService.search(request);
    }

    public HotelShortResponse create(CreateHotelRequest request) {
        return commandService.create(request);
    }

    public void addAmenities(Long id, List<String> amenities) {
        commandService.addAmenities(id, amenities);
    }

    public Map<String, Long> histogram(HistogramType type) {
        return histogramService.histogram(type);
    }
}
