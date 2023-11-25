package com.ecommerce.library.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private long addressId;
    private String city;
    private String state;
    private String detail;
    private String country;

}
