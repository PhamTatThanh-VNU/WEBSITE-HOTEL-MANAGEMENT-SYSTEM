package com.ecommerce.admin.controller;


import com.ecommerce.library.dto.HotelDto;
import com.ecommerce.library.model.Address;
import com.ecommerce.library.model.Hotel;
import com.ecommerce.library.model.RoomType;
import com.ecommerce.library.model.ServiceOfHotel;

import com.ecommerce.library.service.HotelService;
import com.ecommerce.library.service.ServiceofService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.Banner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceofService serviceofService;
    private final HotelService hotelService;
    @GetMapping("/service")
    public String services(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage Service");
        List<ServiceOfHotel> service = serviceofService.findALl();
        List<Hotel> hotel = hotelService.findAll();
        model.addAttribute("services", service);
        model.addAttribute("hotels", hotel);
        model.addAttribute("serviceAdd", new ServiceOfHotel());
        model.addAttribute("size", service.size());
        return "service";
    }
    @PostMapping("/save-service")
    public String save(@ModelAttribute("New Service") ServiceOfHotel newService, Model model, RedirectAttributes redirectAttributes) {
        try {
            serviceofService.save(newService);
            model.addAttribute("New Service", newService);
            redirectAttributes.addFlashAttribute("success", "Add successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            model.addAttribute("New Service", newService);
            redirectAttributes.addFlashAttribute("error",
                    "Error server");
        }
        return "redirect:/service";
    }
    @RequestMapping(value = "/delete-service", method = {RequestMethod.GET, RequestMethod.PUT})
    public String deleteService(Long id, RedirectAttributes redirectAttributes) {
        try {
            serviceofService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/service";
    }
    @RequestMapping(value = "/findByServiceId", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Optional<ServiceOfHotel> findById(Long id) {
        return serviceofService.findById(id);
    }
    @GetMapping("/update-service")
    public String updateService(ServiceOfHotel service, RedirectAttributes redirectAttributes) {
        try {
            serviceofService.updateService(service);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error from server or duplicate name of category, please check again!");
        }
        return "redirect:/service";
    }
}
