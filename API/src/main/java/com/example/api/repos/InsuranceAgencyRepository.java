package com.example.api.repos;

import com.example.api.models.InsuranceAgency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceAgencyRepository extends JpaRepository<InsuranceAgency, Integer> {
}
