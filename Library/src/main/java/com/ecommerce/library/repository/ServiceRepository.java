package com.ecommerce.library.repository;

import com.ecommerce.library.dto.ServiceDto;
import com.ecommerce.library.model.Address;
import com.ecommerce.library.model.ServiceOfHotel;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceOfHotel,Long> {
    @Modifying
    @Query("DELETE FROM ServiceOfHotel s WHERE s.serviceId = :serviceId")
    void deleteByServiceId(@Param("serviceId") Long serviceId);
    @Query("SELECT s FROM ServiceOfHotel as s where s.serviceId = :serviceId")
    ServiceOfHotel findByServiceId(@Param("serviceId") Long serviceId);
    @Query("SELECT new com.ecommerce.library.dto.ServiceDto(s.serviceCost,s.serviceName, s.serviceDescription ,h.hotelId )FROM ServiceOfHotel s " +
            "JOIN Hotel h ON h.hotelId = s.hotel.hotelId")
    List<ServiceDto> allServiceOfHotel();

}
