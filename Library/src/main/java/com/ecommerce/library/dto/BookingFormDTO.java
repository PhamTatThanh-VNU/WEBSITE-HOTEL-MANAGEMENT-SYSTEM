package com.ecommerce.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingFormDTO {
        private String firstName;
        private String lastName;
        private String contactNumber;
        private String email;
        private java.util.Date bookingDate;
        private Integer durationOfStay;
        private Integer totalRoomBooked;
        private java.util.Date checkInDate;
        private java.util.Date checkOutDate;
        private String bookingPaymentType;
        private String specialDescription;
        private Boolean status;
}
