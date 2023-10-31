package com.example.api.repos;

import com.example.api.models.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Integer> {
    List<House> findByUserId(int userId);
    List<House> findBySells(boolean sells);
}
