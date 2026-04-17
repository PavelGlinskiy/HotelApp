package com.example.hotelapp.specification;

import com.example.hotelapp.entity.Hotel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification {

    public static Specification<Hotel> filter(
            String name,
            String brand,
            String city,
            String country,
            List<String> amenities) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (brand != null && !brand.isBlank())
                predicates.add(cb.equal(root.get("brand"), brand));

            if (city != null && !city.isBlank())
                predicates.add(cb.equal(root.get("address").get("city"), city));

            if (country != null && !country.isBlank())
                predicates.add(cb.equal(root.get("address").get("country"), country));

            if (amenities != null && !amenities.isEmpty()) {
                for (String amenity : amenities) {
                    if (amenity != null && !amenity.isBlank()) {
                        predicates.add(cb.isMember(amenity, root.get("amenities")));
                    }
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
