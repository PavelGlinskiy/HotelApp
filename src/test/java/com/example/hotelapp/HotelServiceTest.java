package com.example.hotelapp;

import com.example.hotelapp.dto.CreateHotelRequest;
import com.example.hotelapp.dto.HotelShortResponse;
import com.example.hotelapp.service.HotelCommandService;
import com.example.hotelapp.service.HotelQueryService;
import com.example.hotelapp.service.HotelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelQueryService queryService;

    @Mock
    private HotelCommandService commandService;

    @InjectMocks
    private HotelService service;

    @Test
    void getAll_shouldDelegateToQueryService() {
        List<HotelShortResponse> expected =
                List.of(new HotelShortResponse(1L, "Hilton", "desc", null, "+123"));

        when(queryService.getAll()).thenReturn(expected);

        List<HotelShortResponse> result = service.getAll();

        assertEquals(1, result.size());
        verify(queryService).getAll();
    }

    @Test
    void create_shouldDelegateToCommandService() {
        CreateHotelRequest request = mock(CreateHotelRequest.class);

        HotelShortResponse response =
                new HotelShortResponse(1L, "name", "desc", null, "+123");

        when(commandService.create(request)).thenReturn(response);

        HotelShortResponse result = service.create(request);

        assertEquals("name", result.name());
        verify(commandService).create(request);
    }
}
