package com.ecommerce.library.dto;

import com.ecommerce.library.model.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
    private Long hotelId;
    private String hotelName;
    private String hotelContactNumber;
    private String hotelEmail;
    private String hotelDescription;
    private int hotelFloorCount;
    private int hotelRoomCapacity;
    private String image;
    private Address address;
    private Time check_in_time;
    private Time check_out_time;
}

