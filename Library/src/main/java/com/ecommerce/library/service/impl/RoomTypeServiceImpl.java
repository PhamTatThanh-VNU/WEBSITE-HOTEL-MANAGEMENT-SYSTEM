package com.ecommerce.library.service.impl;

import com.ecommerce.library.model.Room;
import com.ecommerce.library.model.RoomType;
import com.ecommerce.library.repository.RoomTypeRepository;
import com.ecommerce.library.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService{
    private final RoomTypeRepository roomTypeRepository;
    @Override
    public RoomType save(RoomType roomType) {
        RoomType roomType1= new RoomType();
        roomType1.setRoomTypeName(roomType.getRoomTypeName());
        roomType1.setRoomCost(roomType.getRoomCost());
        roomType1.setDescription(roomType.getDescription());
        roomType1.setPetFriendly(roomType.getPetFriendly());
        roomType1.setSmokeFriendly(roomType.getSmokeFriendly());
        return roomTypeRepository.save(roomType1);
    }

    @Override
    public RoomType updateRoomType(RoomType roomType) {
        RoomType roomTypeUpdate = roomTypeRepository.getReferenceById(roomType.getRoomTypeId());
        roomTypeUpdate.setRoomTypeName(roomType.getRoomTypeName());
        roomTypeUpdate.setRoomCost(roomType.getRoomCost());
        roomTypeUpdate.setDescription(roomType.getDescription());
        roomTypeUpdate.setPetFriendly(roomType.getPetFriendly());
        roomTypeUpdate.setSmokeFriendly(roomType.getSmokeFriendly());
        return roomTypeRepository.save(roomTypeUpdate);
    }

    @Override
    public List<RoomType> findALl() {
        return roomTypeRepository.findAll();
    }

    @Override
    public Optional<RoomType> findById(Long id) {
        return roomTypeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        roomTypeRepository.deleteById(id);
    }
}
