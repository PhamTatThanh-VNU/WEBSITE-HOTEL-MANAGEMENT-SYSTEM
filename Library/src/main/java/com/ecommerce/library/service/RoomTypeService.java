package com.ecommerce.library.service;

import com.ecommerce.library.model.Room;
import com.ecommerce.library.model.RoomType;

import java.util.List;
import java.util.Optional;

public interface RoomTypeService  {
    RoomType save(RoomType roomType);

    RoomType updateRoomType (RoomType roomType);
    //List address of Hotel
    List<RoomType> findALl();
    //List address id
    Optional<RoomType> findById(Long id);

    void deleteById(Long id);
}
