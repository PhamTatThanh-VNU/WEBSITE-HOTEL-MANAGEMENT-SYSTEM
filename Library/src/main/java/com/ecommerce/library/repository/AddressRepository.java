package com.ecommerce.library.repository;

import com.ecommerce.library.model.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "update Address a SET " +
            "a.city = COALESCE(:newCity, a.city), " +
            "a.state = COALESCE(:newState, a.state), " +
            "a.detail = COALESCE(:newDetail, a.detail), " +
            "a.country = COALESCE(:newCountry, a.country) " +
            "WHERE a.addressId = :addressId")
    Address updateAddress( String newCity,  String newState,
                       String newDetail,  String newCountry,
                        Long addressId);


    @Query("SELECT a FROM Address a WHERE a.city = :cityName")
    List<Address> findByCity(@Param("cityName") String cityName);

}
