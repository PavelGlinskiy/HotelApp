package com.example.hotelapp;

import com.example.hotelapp.dto.HistogramRow;
import com.example.hotelapp.entity.Address;
import com.example.hotelapp.entity.Hotel;
import com.example.hotelapp.repository.HotelRepository;
import com.example.hotelapp.specification.HotelSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class HotelRepositoryTest {

    @Autowired
    private HotelRepository repository;

    @Autowired
    private TestEntityManager em;

    private Hotel createHotel(
            String name,
            String brand,
            String city,
            String country,
            Set<String> amenities
    ) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setBrand(brand);

        Address address = new Address();
        address.setCity(city);
        address.setCountry(country);

        hotel.setAddress(address);
        hotel.setAmenities(amenities);

        em.persist(hotel);
        em.flush();

        return hotel;
    }

    @BeforeEach
    void clean() {
        repository.deleteAll();
    }

    @Test
    void countByBrand_shouldGroupCorrectly() {
        createHotel("H1", "Hilton", "Minsk", "BY", Set.of("WiFi"));
        createHotel("H2", "Hilton", "Vilnius", "LT", Set.of("Pool"));
        createHotel("H3", "Marriott", "Vilnius", "LT", Set.of("WiFi"));

        List<HistogramRow> result = repository.countByBrand();

        assertEquals(2, result.size());

        assertTrue(result.stream()
                .anyMatch(r -> r.getKey().equals("Hilton") && r.getCount() == 2));

        assertTrue(result.stream()
                .anyMatch(r -> r.getKey().equals("Marriott") && r.getCount() == 1));
    }

    @Test
    void countByCity_shouldGroupCorrectly() {
        createHotel("H1", "Hilton", "Minsk", "BY", Set.of("WiFi"));
        createHotel("H2", "Hilton", "Minsk", "BY", Set.of("Pool"));
        createHotel("H3", "Marriott", "Vilnius", "LT", Set.of("WiFi"));

        List<HistogramRow> result = repository.countByCity();

        assertEquals(2, result.size());

        assertTrue(result.stream()
                .anyMatch(r -> r.getKey().equals("Minsk") && r.getCount() == 2));
    }

    @Test
    void countByCountry_shouldGroupCorrectly() {
        createHotel("H1", "Hilton", "Minsk", "BY", Set.of("WiFi"));
        createHotel("H2", "Hilton", "Vilnius", "LT", Set.of("Pool"));
        createHotel("H3", "Marriott", "Vilnius", "LT", Set.of("WiFi"));

        List<HistogramRow> result = repository.countByCountry();

        assertEquals(2, result.size());

        assertTrue(result.stream()
                .anyMatch(r -> r.getKey().equals("LT") && r.getCount() == 2));
    }

    @Test
    void countByAmenities_shouldGroupCorrectly() {
        createHotel("H1", "Hilton", "Minsk", "BY", Set.of("WiFi", "Pool"));
        createHotel("H2", "Hilton", "Vilnius", "LT", Set.of("WiFi"));
        createHotel("H3", "Marriott", "Vilnius", "LT", Set.of("Pool"));

        List<HistogramRow> result = repository.countByAmenities();

        assertEquals(2, result.size());

        assertTrue(result.stream()
                .anyMatch(r -> r.getKey().equals("WiFi") && r.getCount() == 2));

        assertTrue(result.stream()
                .anyMatch(r -> r.getKey().equals("Pool") && r.getCount() == 2));
    }

    @Test
    void specification_shouldFilterByCityAndBrand() {
        createHotel("H1", "Hilton", "Minsk", "BY", Set.of("WiFi"));
        createHotel("H2", "Marriott", "Vilnius", "LT", Set.of("Pool"));

        Specification<Hotel> spec = HotelSpecification.filter(
                null,
                "Hilton",
                "Minsk",
                null,
                List.of("WiFi")
        );

        List<Hotel> result = repository.findAll(spec);

        assertEquals(1, result.size());
        assertEquals("Hilton", result.getFirst().getBrand());
        assertEquals("Minsk", result.getFirst().getAddress().getCity());
    }
}
