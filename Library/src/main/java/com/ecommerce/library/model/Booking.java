package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "duration_of_stay")
    private int durationOfStay;

    @Column(name = "check_in_date")
    private Date checkInDate;

    @Column(name = "check_out_date")
    private Date checkOutDate;

    @Column(name = "booking_status")
    private Boolean status;

    @Column(name = "total_rooms_booked")
    private int totalRoomsBooked;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id" ,referencedColumnName = "hotel_id")
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_id" ,referencedColumnName = "guest_id")
    private Guests guest;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

}
