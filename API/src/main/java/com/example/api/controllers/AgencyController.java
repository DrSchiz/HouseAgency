package com.example.api.controllers;

import com.example.api.models.City;
import com.example.api.models.InsuranceAgency;
import com.example.api.models.modelUser;
import com.example.api.repos.InsuranceAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AgencyController {
    @Autowired
    public InsuranceAgencyRepository agency;

    @RequestMapping(value = "/agencies", method = RequestMethod.GET)
    public List<InsuranceAgency> getAgencies() {
        return agency.findAll();
    }

    @RequestMapping(value = "/agencies/{id}", method = RequestMethod.GET)
    public InsuranceAgency getAgency(@PathVariable int id) {
        return agency.findById(id).orElseThrow();
    }
}
