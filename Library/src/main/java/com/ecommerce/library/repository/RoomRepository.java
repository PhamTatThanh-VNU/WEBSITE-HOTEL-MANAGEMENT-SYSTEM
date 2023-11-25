package com.ecommerce.library.repository;

import com.ecommerce.library.dto.RoomImgDTO;
import com.ecommerce.library.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    @Query("SELECT NEW com.ecommerce.library.dto.RoomImgDTO(r.image, r.image_1, r.image_2 )" +
            "FROM Room r WHERE r.roomId = :roomId")
    RoomImgDTO getRoomImagesById(Long roomId);

}
