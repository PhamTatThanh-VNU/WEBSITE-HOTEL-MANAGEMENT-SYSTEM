package com.ecommerce.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String guestName;
    private String contactNumber;
    private String email;
    private String hotel;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer totalRoomsBooked;
    private Integer durationOfStay;
    private double totalAmount;
    private String roomInfo;
    private Boolean status;
    private Long bookingId;
    private Long roomId;
    private Long guestId;
}
