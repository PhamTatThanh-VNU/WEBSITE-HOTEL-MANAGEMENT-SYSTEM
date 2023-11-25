package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services", uniqueConstraints = @UniqueConstraint(columnNames = "service_id"))
public class ServiceOfHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "service_description", columnDefinition = "TEXT")
    private String serviceDescription;

    @Column(name = "service_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal serviceCost;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id")
    private Hotel hotel;
}
