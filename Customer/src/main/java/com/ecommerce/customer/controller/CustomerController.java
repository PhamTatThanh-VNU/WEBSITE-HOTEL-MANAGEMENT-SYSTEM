package com.ecommerce.customer.controller;


import ch.qos.logback.core.model.ImplicitModel;
import com.ecommerce.library.dto.HotelDto;
import com.ecommerce.library.dto.HotelDto2;
import com.ecommerce.library.dto.ServiceDto;
import com.ecommerce.library.model.Hotel;
import com.ecommerce.library.model.Room;
import com.ecommerce.library.repository.HotelRepository;
import com.ecommerce.library.service.HotelService;
import com.ecommerce.library.service.RoomService;
import com.ecommerce.library.service.ServiceofService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final HotelService hotelService;
    private final RoomService roomService;
    private final ServiceofService service;
    /*@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
    public String home() {
        return "index";
    }*/
    @GetMapping("/customer")
    public String customer(){
        return "index";
    }
    @GetMapping("/booking")
    public String booking(Model model) {
        return "booking";
    }
    @GetMapping("/room")
    public String showRooms(Model model){
        List<HotelDto2> hotels= hotelService.getHotelDetails();
        model.addAttribute("hotels",hotels);
        List<ServiceDto> services = service.allServiceOfHotel();
        model.addAttribute("services",services);
        return "room";
    }
    @GetMapping("/search-hotels")
    public String searchProduct(@RequestParam(value = "keywordSearch") String keyword,
                                Model model) {
        List<HotelDto2> hotelResult = hotelService.searchHotels(keyword);
        List<ServiceDto> services = service.allServiceOfHotel();
        model.addAttribute("title", "Result Search Products");
        model.addAttribute("hotelResults",hotelResult);
        model.addAttribute("services",services);
        return "booking-result";

    }
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
    @GetMapping("/service")
    public String service(){
        return "service";
    }
}
