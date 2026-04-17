package com.example.hotelapp;

import com.example.hotelapp.dto.CreateHotelRequest;
import com.example.hotelapp.dto.HotelShortResponse;
import com.example.hotelapp.entity.Hotel;
import com.example.hotelapp.exception.AmenityAlreadyExistsException;
import com.example.hotelapp.mapper.HotelMapper;
import com.example.hotelapp.repository.HotelRepository;
import com.example.hotelapp.service.HotelCommandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelCommandServiceTest {

    @Mock
    private HotelRepository repository;

    @Mock
    private HotelMapper mapper;

    @InjectMocks
    private HotelCommandService service;

    @Test
    void create_shouldSaveHotel() {
        CreateHotelRequest request = mock(CreateHotelRequest.class);

        Hotel hotel = new Hotel();
        HotelShortResponse dto =
                new HotelShortResponse(1L, "name", "desc", null, "+123");

        when(mapper.toEntity(request)).thenReturn(hotel);
        when(repository.save(hotel)).thenReturn(hotel);
        when(mapper.toShort(hotel)).thenReturn(dto);

        HotelShortResponse result = service.create(request);

        assertEquals("name", result.name());
        verify(repository).save(hotel);
    }

    @Test
    void addAmenities_shouldAddSuccessfully() {
        Hotel hotel = new Hotel();
        hotel.setAmenities(new HashSet<>());

        when(repository.findById(1L)).thenReturn(Optional.of(hotel));

        service.addAmenities(1L, List.of("WiFi", "Pool"));

        assertTrue(hotel.getAmenities().contains("WiFi"));
        assertTrue(hotel.getAmenities().contains("Pool"));

        verify(repository).save(hotel);
    }

    @Test
    void addAmenities_shouldThrowOnDuplicate() {
        Hotel hotel = new Hotel();
        hotel.setAmenities(new HashSet<>(Set.of("WiFi")));

        when(repository.findById(1L)).thenReturn(Optional.of(hotel));

        assertThrows(AmenityAlreadyExistsException.class, () -> service.addAmenities(1L, List.of("WiFi")));
    }
}
