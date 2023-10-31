package com.example.api.controllers;

import com.example.api.models.House;
import com.example.api.repos.HouseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class HouseController {
    @Autowired
    public HouseRepository houseRepository;

    @RequestMapping(value = "/houses", method = RequestMethod.GET)
    public List<House> getHouses() {
        return houseRepository.findBySells(true);
    }

    @RequestMapping(value = "/houses/{id}", method = RequestMethod.GET)
    public House getHouses(@PathVariable int id) {
        return houseRepository.findById(id).orElseThrow();
    }

    @RequestMapping(value = "/houses/{id}", method = RequestMethod.PUT)
    public House putHouse(@PathVariable int id, @Valid @RequestBody House house) {
        return houseRepository.findById(id).map(house1 -> {
            house1.setUser(house.getUser());
            return houseRepository.save(house);
        }).orElseThrow();
    }
}
