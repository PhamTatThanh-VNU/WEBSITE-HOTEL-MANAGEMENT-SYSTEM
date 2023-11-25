package com.ecommerce.library.repository;

import com.ecommerce.library.model.Guests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface GuestsRepository extends JpaRepository<Guests,Long> {
}
