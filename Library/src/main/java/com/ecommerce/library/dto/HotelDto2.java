package com.ecommerce.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto2 {
    private Long hotelId;
    private String hotelName;
    private String hotelContactNumber;
    private String address;
    private String roomTypeName;
    private Integer smokeFriendly;
    private Integer petFriendly;
    private Double roomCost;
    private String roomNumber;
    private Boolean status;
    private Long roomId;
    private String image;
}
