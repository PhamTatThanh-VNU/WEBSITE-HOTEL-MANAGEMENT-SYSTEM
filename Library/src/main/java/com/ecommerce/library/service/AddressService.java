package com.ecommerce.library.service;

import com.ecommerce.library.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address save(Address address);

    Address updateAddress(Address address);
    //List address of Hotel
    List<Address> findALl();
    //List address id
    Optional<Address> findById(Long id);

    void deleteById(Long id);
}
