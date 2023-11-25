package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address", uniqueConstraints = @UniqueConstraint(columnNames = "address_id"))
public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "detail")
    private String detail;

    @Column(name = "country")
    private String country;

}
