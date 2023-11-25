package com.ecommerce.library.dto;

import com.ecommerce.library.model.Hotel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
    private BigDecimal serviceCost;
    private String serviceName;
    private String serviceDescription;
    private Long hotelId;
}
