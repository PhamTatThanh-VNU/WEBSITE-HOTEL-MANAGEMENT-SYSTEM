package com.ecommerce.customer.controller;

import com.ecommerce.library.dto.BookingFormDTO;
import com.ecommerce.library.dto.RoomImgDTO;
import com.ecommerce.library.model.Hotel;
import com.ecommerce.library.model.Room;
import com.ecommerce.library.service.BookingService;
import com.ecommerce.library.service.HotelService;
import com.ecommerce.library.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookingController {
    private final HotelService hotelService;
    private final RoomService roomService;
    private final BookingService bookingService;
    @GetMapping("/room-booking/roomId={id}/hotelId={id1}")
    public String roomBooking(@PathVariable("id")  Long room_id,
                              @PathVariable("id1") Long hotel_id,
                              Model model) {
        RoomImgDTO roomImgDTOS = roomService.getRoomImagesById(room_id);
        model.addAttribute("roomImg", roomImgDTOS);
        model.addAttribute("hotelFindId", hotel_id);
        model.addAttribute("roomFindId", room_id);
        model.addAttribute("bookingForm",new BookingFormDTO());
        return "room-booking";
    }
    @PostMapping("/room-booking/roomId={id}/hotelId={id1}")
    public String processBookingForm( @PathVariable("id") Long room_id,
                                     @PathVariable("id1") Long hotel_id,
                                      @ModelAttribute("bookingForm") BookingFormDTO bookingFormDTO,
                                     Model model, RedirectAttributes redirectAttributes) {
        // Validate and process the form data
        // Save data to the database using your service classes
        try {
            bookingService.processBooking(bookingFormDTO, room_id, hotel_id);
            redirectAttributes.addFlashAttribute("success", "Booking successfully!");
        }
        catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to booking");
        }
        // Redirect to a success page or display a confirmation message
        return "redirect:/room";
    }
    @GetMapping("/view-details/roomId={id}")
    public String viewDetails(@PathVariable("id")  Long room_id, Model model) {
        RoomImgDTO roomImgDTOS = roomService.getRoomImagesById(room_id);
        model.addAttribute("roomFindId", room_id);
        model.addAttribute("image", roomImgDTOS);
        return "room-details";
    }
    @PostMapping ("/view-details/roomId={id}")
    public String getViewDetails(@PathVariable("id")  Long room_id, Model model) {
        return "room-details";
    }
}
