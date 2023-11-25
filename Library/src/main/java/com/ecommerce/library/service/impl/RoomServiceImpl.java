package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.RoomImgDTO;
import com.ecommerce.library.model.Room;
import com.ecommerce.library.repository.RoomRepository;
import com.ecommerce.library.service.RoomService;
import com.ecommerce.library.utils.ImageUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ImageUpload imageUpload;
    @Override
    public Room save(MultipartFile imageRoom, MultipartFile imageRoom1, MultipartFile imageRoom2, Room room) {
        try {
            Room room1 = new Room();

            if (imageRoom != null && imageRoom.getBytes().length > 0) {
                imageUpload.uploadFile(imageRoom);
                room1.setImage(Base64.getEncoder().encodeToString(imageRoom.getBytes()));
            } else {
                room1.setImage(null);
            }

            if (imageRoom1 != null && imageRoom1.getBytes().length > 0) {
                imageUpload.uploadFile(imageRoom1);
                room1.setImage_1(Base64.getEncoder().encodeToString(imageRoom1.getBytes()));
            } else {
                room1.setImage_1(null);
            }

            if (imageRoom2 != null && imageRoom2.getBytes().length > 0) {
                imageUpload.uploadFile(imageRoom2);
                room1.setImage_2(Base64.getEncoder().encodeToString(imageRoom2.getBytes()));
            } else {
                room1.setImage_2(null);
            }

            room1.setRoomNumber(room.getRoomNumber());
            room1.setHotel(room.getHotel());
            room1.setRoomType(room.getRoomType());
            room1.setStatus(room.getStatus());

            return roomRepository.save(room1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Room updateRoom(MultipartFile imageRoom, MultipartFile imageRoom1, MultipartFile imageRoom2, Room room) {
        try {
            Room roomUpdate = roomRepository.getReferenceById(room.getRoomId());

            if (imageRoom != null && imageRoom.getBytes().length > 0) {
                if (imageUpload.checkExist(imageRoom)) {
                    roomUpdate.setImage(Base64.getEncoder().encodeToString(imageRoom.getBytes()));
                } else {
                    imageUpload.uploadFile(imageRoom);
                    roomUpdate.setImage(Base64.getEncoder().encodeToString(imageRoom.getBytes()));
                }
            }

            if (imageRoom1 != null && imageRoom1.getBytes().length > 0) {
                if (imageUpload.checkExist(imageRoom1)) {
                    roomUpdate.setImage_1(Base64.getEncoder().encodeToString(imageRoom1.getBytes()));
                } else {
                    imageUpload.uploadFile(imageRoom1);
                    roomUpdate.setImage_1(Base64.getEncoder().encodeToString(imageRoom1.getBytes()));
                }
            }

            if (imageRoom2 != null && imageRoom2.getBytes().length > 0) {
                if (imageUpload.checkExist(imageRoom2)) {
                    roomUpdate.setImage_2(Base64.getEncoder().encodeToString(imageRoom2.getBytes()));
                } else {
                    imageUpload.uploadFile(imageRoom2);
                    roomUpdate.setImage_2(Base64.getEncoder().encodeToString(imageRoom2.getBytes()));
                }
            }

            roomUpdate.setRoomNumber(room.getRoomNumber());
            roomUpdate.setHotel(room.getHotel());
            roomUpdate.setRoomType(room.getRoomType());

            return roomRepository.save(roomUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  /*  @Override
    public Room updateRoom(Room room) {
            Room roomUpdate = roomRepository.getReferenceById(room.getRoomId());
            roomUpdate.setRoomNumber(room.getRoomNumber());
            return roomRepository.save(roomUpdate);
    }*/

    @Override
    public List<Room> findALl() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public RoomImgDTO getRoomImagesById(Long roomId) {
        return roomRepository.getRoomImagesById(roomId);
    }

    @Override
    public void deleteById(Long id) {
        Room rooms = roomRepository.getReferenceById(id);
        rooms.setHotel(null);
        rooms.setRoomType(null);
        roomRepository.deleteById(id);
    }

    @Override
    public void enableById(Long id) {
        Room room = roomRepository.getReferenceById(id);
        room.setStatus(false);
        roomRepository.save(room);
    }

    @Override
    public void activeById(Long id) {
        Room room = roomRepository.getReferenceById(id);
        room.setStatus(true);
        roomRepository.save(room);

    }
}
