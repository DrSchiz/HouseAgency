package com.example.api.controllers;

import com.example.api.models.*;
import com.example.api.repos.FeedbackRepository;
import com.example.api.repos.HouseRepository;
import com.example.api.repos.PassportRepository;
import com.example.api.repos.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public FeedbackRepository feedbackRepository;

    @Autowired
    public PassportRepository passportRepository;

    @Autowired
    public HouseRepository houseRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<modelUser> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public modelUser postUser(@Valid @RequestBody modelUser user) {
        user.setRoles(Collections.singleton(roleEnum.USER));
        return userRepository.save(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public modelUser getUser(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @RequestMapping(value = "/users/user/{username}", method = RequestMethod.GET)
    public modelUser getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public modelUser putUser(@PathVariable int id, @Valid @RequestBody modelUser user) {
        return userRepository.findById(id).map(modelUser -> {
            modelUser.setUsername(user.getUsername());
            modelUser.setActive(user.isActive());
            modelUser.setPassport(user.getPassport());
            return userRepository.save(modelUser);
        }).orElseThrow();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        List<Feedback> feedbacks = feedbackRepository.findByUserId(id);
        return userRepository.findById(id).map(modelUser -> {
            userRepository.delete(modelUser);
            return ResponseEntity.ok().build();
        }).orElseThrow();
    }

    @RequestMapping(value = "/users/{id}/feedbacks", method = RequestMethod.GET)
    public List<Feedback> getFeedbacks(@PathVariable int id) {
        return feedbackRepository.findByUserId(id);
    }

    @RequestMapping(value = "/users/{id}/feedbacks", method = RequestMethod.POST)
    public Feedback postFeedback(@PathVariable int id, @Valid @RequestBody Feedback feedback) {
        modelUser user = userRepository.findById(id).orElseThrow();
        feedback.setUser(user);
        return feedbackRepository.save(feedback);
    }

    @RequestMapping(value = "/users/{id}/feedbacks/{idFeedback}", method = RequestMethod.GET)
    public Feedback getFeedback(@PathVariable int id, @PathVariable int idFeedback) {
        return feedbackRepository.findById(idFeedback).orElseThrow();
    }

    @RequestMapping(value = "/users/{id}/feedbacks/{idFeedback}", method = RequestMethod.PUT)
    public Feedback putFeedback(@PathVariable int id, @PathVariable int idFeedback, @Valid @RequestBody Feedback feedback) {
        return feedbackRepository.findById(idFeedback).map(feedback1 -> {
            feedback1.setFeedbackText(feedback.getFeedbackText());
            return feedbackRepository.save(feedback1);
        }).orElseThrow();
    }

    @RequestMapping(value = "/users/{id}/feedbacks/{idFeedback}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFeedback(@PathVariable int id, @PathVariable int idFeedback) {
        return feedbackRepository.findById(idFeedback).map(feedback -> {
            feedbackRepository.delete(feedback);
            return ResponseEntity.ok().build();
        }).orElseThrow();
    }

    @RequestMapping(value = "/users/{id}/houses", method = RequestMethod.GET)
    public List<House> getUserHouses(@PathVariable int id) {
        return houseRepository.findByUserId(id);
    }

    @RequestMapping(value = "/users/{id}/houses", method = RequestMethod.POST)
    public House postHouse(@PathVariable int id, @Valid @RequestBody House house) {
        house.setUser(userRepository.findById(id).orElseThrow());
        return houseRepository.save(house);
    }

    @RequestMapping(value = "/users/{id}/houses/{idHouse}", method = RequestMethod.GET)
    public House geUserHouse(@PathVariable int id, @PathVariable int idHouse) {
        return houseRepository.findById(idHouse).orElseThrow();
    }

    @RequestMapping(value = "/users/{id}/houses/{idHouse}", method = RequestMethod.PUT)
    public House putUserHouse(@PathVariable int id, @PathVariable int idHouse, @Valid @RequestBody House house) {
        return houseRepository.findById(idHouse).map(house1 -> {
            house1.setAdress(house.getAdress());
            house1.setCity(house.getCity());
            house1.setStatusHouse(house.getStatusHouse());
            house1.setDescription(house.getDescription());
            house1.setPrice(house.getPrice());
            house1.setSells(house.isSells());
            house1.setInsuranceAgency(house.getInsuranceAgency());
            return houseRepository.save(house1);
        }).orElseThrow();
    }

    @RequestMapping(value = "/users/{id}/houses/{idHouse}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserHouse(@PathVariable int id, @PathVariable int idHouse) {
        return houseRepository.findById(idHouse).map(house -> {
            houseRepository.delete(house);
            return ResponseEntity.ok().build();
        }).orElseThrow();
    }

}
