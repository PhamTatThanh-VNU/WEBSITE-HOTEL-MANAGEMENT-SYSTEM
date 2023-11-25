package com.ecommerce.library.service;

import com.ecommerce.library.dto.ServiceDto;
import com.ecommerce.library.model.ServiceOfHotel;

import java.util.List;
import java.util.Optional;

public interface ServiceofService {
    ServiceOfHotel save(ServiceOfHotel service);

    ServiceOfHotel updateService (ServiceOfHotel service);
    //List address of Hotel
    List<ServiceOfHotel> findALl();
    //List address id
    Optional<ServiceOfHotel> findById(Long id);
    List<ServiceDto> allServiceOfHotel();
    void deleteById(Long id);
}
