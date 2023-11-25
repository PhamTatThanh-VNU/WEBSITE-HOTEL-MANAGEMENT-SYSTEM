package com.ecommerce.library.repository;

import com.ecommerce.library.model.RoomsBooked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookedRepository extends JpaRepository<RoomsBooked,Long> {
}
