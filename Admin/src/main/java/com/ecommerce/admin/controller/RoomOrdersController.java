package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.HotelDto;
import com.ecommerce.library.dto.HotelDto2;
import com.ecommerce.library.model.Address;
import com.ecommerce.library.model.Hotel;
import com.ecommerce.library.model.Room;
import com.ecommerce.library.model.RoomType;
import com.ecommerce.library.repository.RoomRepository;
import com.ecommerce.library.service.HotelService;
import com.ecommerce.library.service.RoomService;
import com.ecommerce.library.service.RoomTypeService;
import com.ecommerce.library.utils.ImageUpload;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RoomOrdersController {
    private final RoomService roomService;
    private final HotelService hotelService;
    private final RoomTypeService roomTypeService;

    @GetMapping("/roomOrders")
    public String rooms(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage Room");
        List<Room> roomOrders = roomService.findALl();
        List<Hotel> hotelSerivce = hotelService.findAll();
        List<RoomType> roomTypes = roomTypeService.findALl();
        model.addAttribute("rooms", roomOrders);
        model.addAttribute("hotels", hotelSerivce);
        model.addAttribute("roomtypes", roomTypes);
        model.addAttribute("roomsAdd", new Room());
        model.addAttribute("size", roomOrders.size());
        return "roomOrders";
    }

    @PostMapping("/save-roomOrders")
    public String save(@ModelAttribute("roomOrders") Room roomOrders,
                        @RequestParam("imageRoom") MultipartFile imageRoom,
                       @RequestParam("imageRoom1") MultipartFile imageRoom1,
                       @RequestParam("imageRoom2") MultipartFile imageRoom2,
                    Model model, RedirectAttributes redirectAttributes) {
        try {
            roomService.save(imageRoom, imageRoom1,imageRoom2,roomOrders);
            model.addAttribute("roomOrders", roomOrders);
            redirectAttributes.addFlashAttribute("success", "Add successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            model.addAttribute("New RoomType", roomOrders);
            redirectAttributes.addFlashAttribute("error",
                    "Error server");
        }
        return "redirect:/roomOrders";
    }

    @RequestMapping(value = "/delete-roomOrders", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        try {
            roomService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/roomOrders";
    }

    @RequestMapping(value = "/enable-room", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes redirectAttributes) {
        try {
            roomService.enableById(id);
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/roomOrders";
    }

    @RequestMapping(value = "/active-room", method = {RequestMethod.PUT, RequestMethod.GET})
    public String active(Long id, RedirectAttributes redirectAttributes) {
        try {
            roomService.activeById(id);
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/roomOrders";
    }

    @GetMapping("/update-roomOrders/{id}")
    public String updateRoomForm(@PathVariable("id") Long id, Model model) {
        List<Room> roomOrders = roomService.findALl();
        model.addAttribute("rooms", roomOrders);
        Optional<Room> optionalRoom = roomService.findById(id);
        Room rooms = optionalRoom.orElse(new Room());
        model.addAttribute("roomUpdate", rooms);
        List<Hotel> hotelForUpdate = hotelService.findAll();
        List<RoomType> roomTypeForUpdate = roomTypeService.findALl();
        model.addAttribute("hotels", hotelForUpdate);
        model.addAttribute("roomTypes", roomTypeForUpdate);
        return "update-roomOrders";
    }

    @PostMapping("/update-roomOrders/{id}")
    public String updateRoomOrders(@ModelAttribute("roomUpdate") Room room,
                                   @RequestParam("imageRoom") MultipartFile imageRoom,
                                   @RequestParam("imageRoom1") MultipartFile imageRoom1,
                                   @RequestParam("imageRoom2") MultipartFile imageRoom2,
                                   RedirectAttributes redirectAttributes,Model model) {
        try {
            roomService.updateRoom(imageRoom,imageRoom1,imageRoom2,room);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server, please try again!");
        }
        return "redirect:/roomOrders";
    }
}
