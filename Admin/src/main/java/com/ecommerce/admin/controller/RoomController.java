package com.ecommerce.admin.controller;

import com.ecommerce.library.model.Address;
import com.ecommerce.library.model.RoomType;
import com.ecommerce.library.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RoomController {
    private final RoomTypeService roomTypeService;
    @GetMapping("/rooms")
    public String rooms(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage Room");
        List<RoomType> roomTypes = roomTypeService.findALl();
        model.addAttribute("roomtypes", roomTypes);
        model.addAttribute("New Room", new RoomType());
        model.addAttribute("size", roomTypes.size());
        return "rooms";
    }
    @PostMapping("/save-room")
    public String save(@ModelAttribute("New Room") RoomType roomType, Model model, RedirectAttributes redirectAttributes) {
        try {
            roomTypeService.save(roomType);
            model.addAttribute("New RoomType", roomType);
            redirectAttributes.addFlashAttribute("success", "Add successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            model.addAttribute("New RoomType", roomType);
            redirectAttributes.addFlashAttribute("error",
                    "Error server");
        }
        return "redirect:/rooms";
    }
    @RequestMapping(value = "/delete-room", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        try {
            roomTypeService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/rooms";
    }
    @RequestMapping(value = "/findRoomById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Optional<RoomType> findRoomById(Long id) {
        return roomTypeService.findById(id);
    }
    @GetMapping("/update-room")
    public String updateRoomType(RoomType roomType, RedirectAttributes redirectAttributes) {
        try {
            roomTypeService.updateRoomType(roomType);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error from server or duplicate name of category, please check again!");
        }
        return "redirect:/rooms";
    }
}
