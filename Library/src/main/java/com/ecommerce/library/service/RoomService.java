package com.ecommerce.library.service;

import com.ecommerce.library.dto.RoomImgDTO;
import com.ecommerce.library.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room save(MultipartFile imageRoom,MultipartFile imageRoom1,MultipartFile imageRoom2,Room room);

    Room updateRoom(MultipartFile imageRoom,MultipartFile imageRoom1,MultipartFile imageRoom2, Room room);
    //List address of Hotel
    List<Room> findALl();
    //List address id
    Optional<Room> findById(Long id);
    RoomImgDTO getRoomImagesById(Long roomId);
    void deleteById(Long id);
    void enableById(Long id);
    void activeById(Long id);
}
