package com.ecommerce.library.service;

import com.ecommerce.library.dto.BookingDTO;
import com.ecommerce.library.dto.BookingFormDTO;
import com.ecommerce.library.dto.RoomDto;

import java.util.List;

public interface BookingService {
    void processBooking(BookingFormDTO bookingForm, Long room_id, Long hotel_id);
    List<BookingDTO> getBookingDTOs();
    void processById(Long id);
    void deleteBooking(Long booking_id, Long room_id, Long guest_id);
    void doneById(Long id);
}
