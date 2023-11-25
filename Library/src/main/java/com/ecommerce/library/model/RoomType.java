package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_type", uniqueConstraints = @UniqueConstraint(columnNames = "room_type_id"))
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Long roomTypeId;

    @Column(name = "room_type_name", nullable = false)
    private String roomTypeName;

    @Column(name = "room_cost", nullable = false)
    private Double roomCost;

    @Column(name = "description")
    private String description;

    @Column(name = "smoke_friendly")
    private Integer smokeFriendly;

    @Column(name = "pet_friendly")
    private Integer petFriendly;

}
