package com.example.api.repos;

import com.example.api.models.modelUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<modelUser, Integer> {
    modelUser findByUsername(String username);
}
