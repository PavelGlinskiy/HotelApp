package com.example.hotelapp.mapper;

import com.example.hotelapp.dto.AddressDto;
import com.example.hotelapp.dto.ArrivalTimeDto;
import com.example.hotelapp.dto.ContactsDto;
import com.example.hotelapp.dto.CreateHotelRequest;
import com.example.hotelapp.dto.HotelFullResponse;
import com.example.hotelapp.dto.HotelShortResponse;
import com.example.hotelapp.entity.Address;
import com.example.hotelapp.entity.ArrivalTime;
import com.example.hotelapp.entity.Contacts;
import com.example.hotelapp.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface HotelMapper {

    @Mapping(target = "address", source = "address")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "arrivalTime", source = "arrivalTime")
    Hotel toEntity(CreateHotelRequest request);

    @Mapping(target = "phone", source = "contacts.phone")
    HotelShortResponse toShort(Hotel entity);

    HotelFullResponse toFull(Hotel entity);
}
