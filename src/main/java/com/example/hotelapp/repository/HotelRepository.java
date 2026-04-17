package com.example.hotelapp.repository;

import com.example.hotelapp.dto.HistogramRow;
import com.example.hotelapp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long>,
        JpaSpecificationExecutor<Hotel> {
    @Query("""
        SELECT h.brand AS key, COUNT(h) AS count
        FROM Hotel h
        GROUP BY h.brand
    """)
    List<HistogramRow> countByBrand();

    @Query("""
        SELECT h.address.city AS key, COUNT(h) AS count
        FROM Hotel h
        GROUP BY h.address.city
    """)
    List<HistogramRow> countByCity();

    @Query("""
        SELECT h.address.country AS key, COUNT(h) AS count
        FROM Hotel h
        GROUP BY h.address.country
    """)
    List<HistogramRow> countByCountry();

    @Query("""
        SELECT a AS key, COUNT(h) AS count
        FROM Hotel h JOIN h.amenities a
        GROUP BY a
    """)
    List<HistogramRow> countByAmenities();
}
