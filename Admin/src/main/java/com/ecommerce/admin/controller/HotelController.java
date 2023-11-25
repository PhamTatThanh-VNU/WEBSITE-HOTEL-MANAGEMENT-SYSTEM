package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.AddressDto;
import com.ecommerce.library.dto.HotelDto;
import com.ecommerce.library.model.Address;
import com.ecommerce.library.model.Hotel;
import com.ecommerce.library.service.AddressService;
import com.ecommerce.library.service.HotelService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

@Controller
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    private final AddressService addressService;


    @GetMapping("/products")
    public String products(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<Hotel> hotels = hotelService.findAll();
        model.addAttribute("hotels", hotels);
        model.addAttribute("size", hotels.size());
        return "products";
    }

    @GetMapping("/search-products/{pageNo}")
    public String searchProduct(@PathVariable("pageNo") int pageNo,
                                @RequestParam(value = "keyword") String keyword,
                                Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Page<HotelDto> hotels = hotelService.searchProducts(pageNo, keyword);
        model.addAttribute("title", "Result Search Products");
        model.addAttribute("size", hotels.getSize());
        model.addAttribute("hotels", hotels);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", hotels.getTotalPages());
        return "product-result";

    }

    @RequestMapping(value = "/delete-product", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deletedProduct(Long id, RedirectAttributes redirectAttributes, Principal principal) {
        try {
            if (principal == null) {
                return "redirect:/login";
            }
            hotelService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Deleted failed!");
        }
        return "redirect:/products";
    }

    @GetMapping("/products/{pageNo}")
    public String allProducts(@PathVariable("pageNo") int pageNo, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (pageNo < 0) {
            pageNo = 0;
        }
        Page<HotelDto> hotels = hotelService.getAllProducts(pageNo);
        model.addAttribute("title", "Manage Products");
        model.addAttribute("size", hotels.getSize());
        model.addAttribute("hotels", hotels);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", hotels.getTotalPages());
        return "products";
    }


    @GetMapping("/add-product")
    public String addProductPage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Add Hotel");
        List<Address> addresses = addressService.findALl();
        model.addAttribute("addresses", addresses);
        model.addAttribute("hotelDto", new HotelDto());
        return "add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("hotelDto") HotelDto hotel,
                              @RequestParam("imageProduct") MultipartFile imageProduct,
                              RedirectAttributes redirectAttributes, Principal principal) {
        try {
            if (principal == null) {
                return "redirect:/login";
            }
            hotelService.save(imageProduct, hotel);
            redirectAttributes.addFlashAttribute("successf", "Add new HOTEL successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new hotel!");
        }
        return "redirect:/products/0";
    }

    @GetMapping("/update-product/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<Address> addresses = addressService.findALl();
        HotelDto hotelDto = hotelService.getById(id);
        model.addAttribute("title", "ADD NEW HOTEL");
        model.addAttribute("addresses", addresses);
        model.addAttribute("hotelDto", hotelDto);
        return "update-product";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(@ModelAttribute("hotelDto") HotelDto hotel,
                                @RequestParam("imageProduct") MultipartFile imageProduct,
                                RedirectAttributes redirectAttributes, Principal principal) {
        try {
            if (principal == null) {
                return "redirect:/login";
            }
            hotelService.update(imageProduct, hotel);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server, please try again!");
        }
        return "redirect:/products/0";
    }
}
