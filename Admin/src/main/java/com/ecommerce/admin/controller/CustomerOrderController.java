package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.BookingDTO;
import com.ecommerce.library.dto.RoomDto;
import com.ecommerce.library.model.Hotel;
import com.ecommerce.library.model.Room;
import com.ecommerce.library.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CustomerOrderController {
    private final BookingService bookingService;
    @GetMapping("/customerOrder")
    public String products(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<BookingDTO> bookingDTOList = bookingService.getBookingDTOs();
        model.addAttribute("customerOrders",bookingDTOList);
        model.addAttribute("size",bookingDTOList.size());
        return "customerOrder";
    }
    @RequestMapping(value = "/done-order", method = {RequestMethod.PUT, RequestMethod.GET})
    public String done(Long id, RedirectAttributes redirectAttributes) {
        try {
            bookingService.doneById(id);
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/customerOrder";
    }
    @RequestMapping(value = "/process-order", method = {RequestMethod.PUT, RequestMethod.GET})
    public String process(Long id, RedirectAttributes redirectAttributes) {
        try {
            bookingService.processById(id);
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/customerOrder";
    }
    @RequestMapping(value = "/delete-orders/{booking_id}/{room_id}/{guest_id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String deleteOrder(@PathVariable("booking_id") Long booking_id,
                              @PathVariable("room_id") Long room_id,
                              @PathVariable("guest_id") Long guest_id ,RedirectAttributes redirectAttributes) {
        try {
            bookingService.deleteBooking(booking_id,room_id,guest_id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/customerOrder";
    }

}
