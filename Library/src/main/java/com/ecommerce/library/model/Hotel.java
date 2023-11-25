package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel", uniqueConstraints = @UniqueConstraint(columnNames = {"hotel_id", "image"}))
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_contact_number")
    private String hotelContactNumber;

    @Column(name = "hotel_email")
    private String hotelEmail;

    @Column(name = "hotel_description")
    private String hotelDescription;

    @Column(name = "hotel_floor_count")
    private int hotelFloorCount;

    @Column(name = "hotel_room_capacity")
    private int hotelRoomCapacity;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
    private Time check_in_time;
    private Time check_out_time;

}
