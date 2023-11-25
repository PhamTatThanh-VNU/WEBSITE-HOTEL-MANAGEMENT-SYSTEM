package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_number")
    private String roomNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = false, foreignKey = @ForeignKey(name = "FK7ulkstykm2bb6ytrv79bk44ki", foreignKeyDefinition = "FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE CASCADE"))
    private Hotel hotel;

    @Column(name = "status")
    private Boolean status;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image_1;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image_2;
}
