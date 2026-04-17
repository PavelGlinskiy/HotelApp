package com.example.hotelapp;

import com.example.hotelapp.dto.HotelFullResponse;
import com.example.hotelapp.dto.HotelShortResponse;
import com.example.hotelapp.entity.Hotel;
import com.example.hotelapp.exception.NotFoundException;
import com.example.hotelapp.mapper.HotelMapper;
import com.example.hotelapp.repository.HotelRepository;
import com.example.hotelapp.service.HotelQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelQueryServiceTest {

    @Mock
    private HotelRepository repository;

    @Mock
    private HotelMapper mapper;

    @InjectMocks
    private HotelQueryService service;

    @Test
    void getAll_shouldReturnMappedList() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);

        HotelShortResponse dto =
                new HotelShortResponse(1L, "Hilton", "desc", null, "+123");

        when(repository.findAll()).thenReturn(List.of(hotel));
        when(mapper.toShort(hotel)).thenReturn(dto);

        List<HotelShortResponse> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Hilton", result.get(0).name());

        verify(repository).findAll();
    }

    @Test
    void getById_shouldReturnHotel() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);

        HotelFullResponse dto =
                new HotelFullResponse(1L, "name", "desc", "brand", null, null, null, List.of());

        when(repository.findById(1L)).thenReturn(Optional.of(hotel));
        when(mapper.toFull(hotel)).thenReturn(dto);

        HotelFullResponse result = service.getById(1L);

        assertEquals(1L, result.id());
    }

    @Test
    void getById_shouldThrowNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getById(1L));
    }
}
