package com.ecommerce.library.service;

import com.ecommerce.library.dto.HotelDto;
import com.ecommerce.library.dto.HotelDto2;
import com.ecommerce.library.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<Hotel> findAll();


    List<HotelDto> allProduct();

    Hotel save(MultipartFile imageProduct, HotelDto product);

    Hotel update(MultipartFile imageProduct, HotelDto productDto);

    void deleteById(Long id);

    HotelDto getById(Long id);

    Optional<Hotel> findById(Long id);


    Page<HotelDto> searchProducts(int pageNo, String keyword);
    List<HotelDto> searchProducts(String keyword);
    List<HotelDto> products();

    Page<HotelDto> getAllProducts(int pageNo);


    List<HotelDto2> getHotelDetails();
    List<HotelDto2> searchHotels(String keyword);
}
