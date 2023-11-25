package com.ecommerce.library.service.impl;

import com.ecommerce.library.model.Address;
import com.ecommerce.library.repository.AddressRepository;
import com.ecommerce.library.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        Address address1 = new Address();
        address1.setCity(address.getCity());
        address1.setState(address.getState());
        address1.setCountry(address.getCountry());
        address1.setDetail(address.getDetail());
        return addressRepository.save(address1);

    }

    @Override
    public Address updateAddress(Address address) {
        Address addressUpdate = addressRepository.getReferenceById(address.getAddressId());
        addressUpdate.setCity(address.getCity());
        addressUpdate.setCountry(address.getCountry());
        addressUpdate.setDetail(address.getDetail());
        addressUpdate.setState(address.getState());
        return addressRepository.save(addressUpdate);
    }

    @Override
    public List<Address> findALl() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}


