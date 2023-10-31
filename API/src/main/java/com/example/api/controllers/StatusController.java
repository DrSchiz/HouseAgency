package com.example.api.controllers;

import com.example.api.models.City;
import com.example.api.models.InsuranceAgency;
import com.example.api.models.StatusHouse;
import com.example.api.repos.InsuranceAgencyRepository;
import com.example.api.repos.StatusHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatusController {
    @Autowired
    public StatusHouseRepository status;

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public List<StatusHouse> getStatuses() {
        return status.findAll();
    }

    @RequestMapping(value = "/statuses/{id}", method = RequestMethod.GET)
    public StatusHouse getStatus(@PathVariable int id) {
        return status.findById(id).orElseThrow();
    }
}
