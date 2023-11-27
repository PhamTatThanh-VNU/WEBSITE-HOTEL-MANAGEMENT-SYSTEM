package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.BookingDTO;
import com.ecommerce.library.dto.BookingFormDTO;
import com.ecommerce.library.dto.RoomDto;
import com.ecommerce.library.model.*;
import com.ecommerce.library.repository.*;
import com.ecommerce.library.service.BookingService;
import com.ecommerce.library.service.HotelService;
import com.ecommerce.library.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final GuestsRepository guestsRepository;
    private final BookingRepository bookingRepository;
    private final RoomBookedRepository roomBookedRepository;
    private final RoomService roomService;
    private final HotelService hotelService;

    public void processBooking(BookingFormDTO bookingForm, Long room_id, Long hotel_id) {
        // Perform validation and processing of the form data
        Optional<Hotel> optionalHotel = hotelService.findById(hotel_id);
        Optional<Room> optionalRoom = roomService.findById(room_id);
        // Save guest information
        Guests guest = new Guests();
        guest.setFirstName(bookingForm.getFirstName());
        guest.setLastName(bookingForm.getLastName());
        guest.setEmail(bookingForm.getEmail());
        guest.setContactNumber(bookingForm.getContactNumber());
        guest.setSpecialDescription(bookingForm.getSpecialDescription());
        guestsRepository.save(guest);

        // Save booking information
        Booking booking = new Booking();
        booking.setBookingDate(convert(bookingForm.getBookingDate()));
        booking.setGuest(guest);
        booking.setStatus(false);
        booking.setCheckInDate(convert(bookingForm.getCheckInDate()));
        booking.setCheckOutDate(convert(bookingForm.getCheckOutDate()));
        booking.calculateDurationOfStay(convert(bookingForm.getCheckInDate()),convert(bookingForm.getCheckOutDate()));
        booking.setTotalRoomsBooked(bookingForm.getTotalRoomBooked());
        optionalHotel.ifPresent(booking::setHotel);


        Booking savedBooking = bookingRepository.save(booking);

        // Save room booked information
        RoomsBooked roomBooked = new RoomsBooked();
        roomBooked.setBooking(savedBooking);
        optionalRoom.ifPresent(roomBooked::setRoom);
        roomBookedRepository.save(roomBooked);
    }
    @Override
    public void deleteBooking(Long booking_id, Long room_id, Long guest_id) {
        RoomsBooked roomsBookedDelete = roomBookedRepository.getReferenceById(room_id);
        roomsBookedDelete.setRoom(null);
        roomsBookedDelete.setBooking(null);
        roomBookedRepository.deleteById(room_id);
        Booking bookingDelete = bookingRepository.getReferenceById(booking_id);
        bookingDelete.setGuest(null);
        bookingDelete.setHotel(null);
        bookingRepository.deleteById(booking_id);
        guestsRepository.deleteById(guest_id);
    }
    @Override
    public List<BookingDTO> getBookingDTOs() {
        return bookingRepository.getBookingDTOs();
    }

    @Override
    public void processById(Long id) {
        Booking booking = bookingRepository.getReferenceById(id);
        booking.setStatus(false);
        bookingRepository.save(booking);
    }

    @Override
    public void doneById(Long id) {
        Booking booking = bookingRepository.getReferenceById(id);
        booking.setStatus(true);
        bookingRepository.save(booking);
    }

    public java.sql.Date convert(java.util.Date utilDate) {
            return (utilDate == null) ? null : new java.sql.Date(utilDate.getTime());
    }

}
