package com.ecommerce.library.repository;

import com.ecommerce.library.dto.HotelDto2;
import com.ecommerce.library.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("select h from Hotel h where h.hotelId is not null")
    List<Hotel> getAllProduct();
    @Query("SELECT h FROM Hotel h WHERE LOWER(h.hotelName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(h.hotelDescription) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(h.address.city) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(h.address.state) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(h.address.detail) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Hotel> searchProducts(String keyword);
    @Query("SELECT h FROM Hotel h WHERE LOWER(h.hotelName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(h.hotelDescription) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(h.address.city) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(h.address.state) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(h.address.detail) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Hotel> findAllByNameOrDescription(String keyword);

    @Query("SELECT NEW com.ecommerce.library.dto.HotelDto2(" +
            "h.hotelId, " +
            "h.hotelName," +
            "h.hotelContactNumber," +
            "CONCAT(a.detail, ', ', a.state, ', ', a.city, ', ', a.country), " +
            "rt.roomTypeName, " +
            "rt.smokeFriendly, " +
            "rt.petFriendly, " +
            "rt.roomCost, " +
            "r.roomNumber, " +
            "r.status, " +
            "r.roomId, " +
            "h.image)" +
            "FROM Hotel h " +
            "JOIN h.address a " +
            "LEFT JOIN Room r ON h.hotelId = r.hotel.hotelId " +
            "LEFT JOIN r.roomType rt")
    List<HotelDto2> getHotelDetails();
    @Query("SELECT NEW com.ecommerce.library.dto.HotelDto2(" +
            "h.hotelId, " +
            "h.hotelName, " +
            "h.hotelContactNumber," +
            "CONCAT(a.detail, ', ', a.state, ', ', a.city, ', ', a.country), " +
            "rt.roomTypeName, " +
            "rt.smokeFriendly, " +
            "rt.petFriendly, " +
            "rt.roomCost, " +
            "r.roomNumber, " +
            "r.status, " +
            "r.roomId, " +
            "h.image) " +
            "FROM Hotel h " +
            "JOIN h.address a " +
            "LEFT JOIN Room r ON h.hotelId = r.hotel.hotelId " +
            "LEFT JOIN r.roomType rt " +
            "WHERE (:keyword IS NULL OR LOWER(h.hotelName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "OR (:keyword IS NULL OR LOWER(CONCAT(a.detail, ', ', a.state, ', ', a.city, ', ', a.country)) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<HotelDto2> searchHotels(String keyword);




}
