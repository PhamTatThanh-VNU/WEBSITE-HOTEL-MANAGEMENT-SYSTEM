package com.ecommerce.library.repository;

import com.ecommerce.library.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository

public interface RoomTypeRepository extends JpaRepository<RoomType,Long> {
}
