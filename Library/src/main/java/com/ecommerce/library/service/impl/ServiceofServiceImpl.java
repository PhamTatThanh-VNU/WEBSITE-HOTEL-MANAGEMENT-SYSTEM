package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.ServiceDto;
import com.ecommerce.library.model.RoomType;
import com.ecommerce.library.model.ServiceOfHotel;
import com.ecommerce.library.repository.ServiceRepository;
import com.ecommerce.library.service.ServiceofService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceofServiceImpl implements ServiceofService {
    private final ServiceRepository serviceRepository;
    public ServiceOfHotel save(ServiceOfHotel service) {
        ServiceOfHotel service1= new ServiceOfHotel();
        service1.setServiceName(service.getServiceName());
        service1.setServiceCost(service.getServiceCost());
        service1.setServiceDescription(service.getServiceDescription());
        service1.setHotel(service.getHotel());
        return serviceRepository.save(service1);
    }

    @Override
    public ServiceOfHotel updateService(ServiceOfHotel service) {
        ServiceOfHotel serviceUpdate = serviceRepository.getReferenceById(service.getServiceId());
        serviceUpdate.setServiceName(service.getServiceName());
        serviceUpdate.setServiceCost(service.getServiceCost());
        serviceUpdate.setServiceDescription(service.getServiceDescription());
        return serviceRepository.save(serviceUpdate);
    }

    @Override
    public List<ServiceOfHotel> findALl() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<ServiceOfHotel> findById(Long id) {
        return serviceRepository.findById(id);
    }
    @Override
    public List<ServiceDto> allServiceOfHotel(){
        return serviceRepository.allServiceOfHotel();
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        serviceRepository.deleteByServiceId(id);
    }
}
