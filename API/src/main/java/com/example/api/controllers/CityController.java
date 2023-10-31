package com.example.api.controllers;

import com.example.api.models.City;
import com.example.api.models.StatusHouse;
import com.example.api.repos.CityRepository;
import com.example.api.repos.StatusHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    public CityRepository city;

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public List<City> getCities() {
        return city.findAll();
    }

    @RequestMapping(value = "/cities/{id}", method = RequestMethod.GET)
    public City getCity(@PathVariable int id) {
        return city.findById(id).orElseThrow();
    }
}
