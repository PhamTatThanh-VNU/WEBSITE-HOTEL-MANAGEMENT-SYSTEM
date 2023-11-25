package com.ecommerce.admin.controller;

import com.ecommerce.library.model.Address;
import com.ecommerce.library.service.AddressService;
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
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/categories")
    public String categories(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage Address");
        List<Address> addresses = addressService.findALl();
        model.addAttribute("addresses", addresses);
        model.addAttribute("New Address", new Address());
        model.addAttribute("size", addresses.size());
        return "categories";
    }

    @PostMapping("/save-category")
    public String save(@ModelAttribute("New Address") Address address, Model model, RedirectAttributes redirectAttributes) {
        try {
            addressService.save(address);
            model.addAttribute("New Address", address);
            redirectAttributes.addFlashAttribute("success", "Add successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            model.addAttribute("New Address", address);
            redirectAttributes.addFlashAttribute("error",
                    "Error server");
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/findById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Optional<Address> findById(Long id) {
        return addressService.findById(id);
    }

    @GetMapping("/update-category")
    public String updateAddress(Address address, RedirectAttributes redirectAttributes) {
        try {
            addressService.updateAddress(address);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error from server or duplicate name of category, please check again!");
        }
        return "redirect:/categories";
    }


    @RequestMapping(value = "/delete-category", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        try {
            addressService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/categories";
    }


}
