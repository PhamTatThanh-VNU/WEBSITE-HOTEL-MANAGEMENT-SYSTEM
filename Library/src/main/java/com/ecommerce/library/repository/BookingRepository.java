package com.ecommerce.library.repository;

import com.ecommerce.library.dto.BookingDTO;
import com.ecommerce.library.dto.RoomDto;
import com.ecommerce.library.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{
    @Query("SELECT NEW com.ecommerce.library.dto.BookingDTO(" +
            "CONCAT(g.firstName, ' ', g.lastName), " +
            "g.contactNumber," +
            "g.email," +
            "CONCAT(h.hotelName, '-', CONCAT(a.detail, ', ', a.state, ', ', a.city, ', ', a.country)), " +
            "b.checkInDate, b.checkOutDate, " +
            "b.totalRoomsBooked, b.durationOfStay, " +
            "ROUND(b.durationOfStay * AVG(rt.roomCost)  * b.totalRoomsBooked, 2)," +
            "CONCAT(r.roomNumber,'-',rt.roomTypeName)," +
            "b.status," +
            "b.bookingId," +
            "rb.id," +
            "g.id) " +
            "FROM Booking b " +
            "JOIN b.hotel h " +
            "JOIN h.address a " +
            "JOIN b.guest g " +
            "JOIN RoomsBooked rb ON rb.booking.bookingId = b.bookingId " +
            "JOIN rb.room r " +
            "JOIN r.roomType rt " +
            "JOIN ServiceOfHotel s ON s.hotel.hotelId = h.hotelId " +
            "GROUP BY b.bookingId, r.roomNumber, rt.roomTypeName,rb.id,g.id")
    List<BookingDTO> getBookingDTOs();

}
