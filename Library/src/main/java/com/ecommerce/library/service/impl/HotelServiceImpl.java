package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.HotelDto;
import com.ecommerce.library.dto.HotelDto2;
import com.ecommerce.library.model.Hotel;
import com.ecommerce.library.repository.HotelRepository;
import com.ecommerce.library.service.HotelService;
import com.ecommerce.library.utils.ImageUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final ImageUpload imageUpload;

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<HotelDto> products() {
        return transferData(hotelRepository.getAllProduct());
    }

    @Override
    public List<HotelDto> allProduct() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelDto> hotelDtos = transferData(hotels);
        return hotelDtos;
    }

    @Override
    public Hotel save(MultipartFile imageHotel, HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        try {
            if (imageHotel == null) {
                hotel.setImage(null);
            } else {
                imageUpload.uploadFile(imageHotel);
                hotel.setImage(Base64.getEncoder().encodeToString(imageHotel.getBytes()));
            }
            hotel.setAddress(hotelDto.getAddress());
            hotel.setHotelName(hotelDto.getHotelName());
            hotel.setHotelDescription(hotelDto.getHotelDescription());
            hotel.setHotelEmail(hotelDto.getHotelEmail());
            hotel.setHotelContactNumber(hotelDto.getHotelContactNumber());
            hotel.setHotelFloorCount(hotelDto.getHotelFloorCount());
            hotel.setHotelRoomCapacity(hotelDto.getHotelRoomCapacity());
            return hotelRepository.save(hotel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Hotel update(MultipartFile imageProduct, HotelDto hotelDto) {
        try {
            Hotel hotel = hotelRepository.getReferenceById(hotelDto.getHotelId());
            if (imageProduct.getBytes().length > 0) {
                if (imageUpload.checkExist(imageProduct)) {
                    hotel.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
                } else {
                    imageUpload.uploadFile(imageProduct);
                    hotel.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
                }
            }
            hotel.setAddress(hotelDto.getAddress());
            hotel.setHotelId(hotelDto.getHotelId());
            hotel.setHotelName(hotelDto.getHotelName());
            hotel.setHotelDescription(hotelDto.getHotelDescription());
            hotel.setHotelEmail(hotelDto.getHotelEmail());
            hotel.setHotelContactNumber(hotelDto.getHotelContactNumber());
            hotel.setHotelFloorCount(hotelDto.getHotelFloorCount());
            hotel.setHotelRoomCapacity(hotelDto.getHotelRoomCapacity());
            return hotelRepository.save(hotel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        Hotel hotel = hotelRepository.getReferenceById(id);
        hotel.setAddress(null);
        hotelRepository.deleteById(id);
    }

    @Override
    public HotelDto getById(Long id) {
        HotelDto hotelDto = new HotelDto();
        Hotel hotel = hotelRepository.getReferenceById(id);
        hotelDto.setHotelId(hotel.getHotelId());
        hotelDto.setAddress(hotel.getAddress());
        hotelDto.setHotelName(hotel.getHotelName());
        hotelDto.setHotelDescription(hotel.getHotelDescription());
        hotelDto.setHotelEmail(hotel.getHotelEmail());
        hotelDto.setHotelContactNumber(hotel.getHotelContactNumber());
        hotelDto.setHotelFloorCount(hotel.getHotelFloorCount());
        hotelDto.setHotelRoomCapacity(hotel.getHotelRoomCapacity());
        hotelDto.setImage(hotel.getImage());
        return hotelDto;
    }

    private Page toPage(List list, Pageable pageable) {
        // Kiểm tra pageable và list có giá trị null hay không
        if (pageable == null || list == null) {
            return Page.empty();
        }

        // Kiểm tra pageSize có giá trị hợp lệ hay không
        if (pageable.getPageSize() <= 0) {
            return Page.empty();
        }

        // Tính toán các chỉ số
        int totalElements = list.size();
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min((int) (pageable.getOffset() + pageable.getPageSize()), totalElements);

        // Kiểm tra nếu trang hiện tại vượt quá kích thước danh sách
        if (startIndex >= totalElements) {
            return Page.empty();
        }

        // Trích xuất danh sách con từ danh sách tổng
        List subList = list.subList(startIndex, endIndex);

        // Trả về đối tượng PageImpl
        return new PageImpl(subList, pageable, totalElements);
    }


    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public Page<HotelDto> searchProducts(int pageNo, String keyword) {
        List<Hotel> products = hotelRepository.findAllByNameOrDescription(keyword);
        List<HotelDto> hotelDtoList = transferData(products);
        Pageable pageable = PageRequest.of(pageNo, 5);
        return (Page<HotelDto>) toPage(hotelDtoList, pageable);
    }

    @Override
    public Page<HotelDto> getAllProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<HotelDto> hotelDtoLists = this.allProduct();
        return (Page<HotelDto>) toPage(hotelDtoLists, pageable);
    }

    private List<HotelDto> transferData(List<Hotel> hotels) {
        List<HotelDto> hotelDtos = new ArrayList<>();
        for (Hotel hotel : hotels) {
            HotelDto hotelDto = new HotelDto();
            hotelDto.setHotelId(hotel.getHotelId());
            hotelDto.setAddress(hotel.getAddress());
            hotelDto.setHotelName(hotel.getHotelName());
            hotelDto.setHotelDescription(hotel.getHotelDescription());
            hotelDto.setHotelEmail(hotel.getHotelEmail());
            hotelDto.setHotelContactNumber(hotel.getHotelContactNumber());
            hotelDto.setHotelFloorCount(hotel.getHotelFloorCount());
            hotelDto.setHotelRoomCapacity(hotel.getHotelRoomCapacity());
            hotelDto.setImage(hotel.getImage());
            hotelDtos.add(hotelDto);
        }
        return hotelDtos;
    }
    @Override
    public List<HotelDto> searchProducts(String keyword) {
        return transferData(hotelRepository.searchProducts(keyword));
    }
    @Override
    public List<HotelDto2> getHotelDetails(){
        return hotelRepository.getHotelDetails();
    }

    @Override
    public List<HotelDto2> searchHotels(String keyword) {
        return hotelRepository.searchHotels(keyword);
    }

}
