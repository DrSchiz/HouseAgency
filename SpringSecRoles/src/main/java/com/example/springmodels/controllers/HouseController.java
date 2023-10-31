package com.example.springmodels.controllers;

import com.example.springmodels.models.*;
import jdk.jshell.Snippet;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/houses")
public class HouseController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getHomes(Model model, Principal principal) {
        String username = principal.getName();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<House[]> response = restTemplate.getForEntity("http://localhost:8090/api/houses", House[].class);
        House[] houses = response.getBody();
        model.addAttribute("houses", houses);
        model.addAttribute("username", username);
        return "houses";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String patchHome(Model model, Principal principal, @PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        House house = restTemplate.getForObject("http://localhost:8090/api/houses/"+id, House.class);
        modelUser user = restTemplate.getForObject("http://localhost:8090/api/users/user/"+principal.getName(), modelUser.class);
        house.setUser(user);
        restTemplate.put("http://localhost:8090/api/houses/"+house.getId(), house, House.class);
        ResponseEntity<House[]> response = restTemplate.getForEntity("http://localhost:8090/api/houses", House[].class);
        House[] houses = response.getBody();
        model.addAttribute("houses", houses);
        String username = principal.getName();
        model.addAttribute("username", username);
        return "houses";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/house", method = RequestMethod.GET)
    public String getHouse(@ModelAttribute House house,
                           Principal principal,
                           Model model) {
        model.addAttribute("house", house);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsuranceAgency[]> response = restTemplate.getForEntity("http://localhost:8090/api/agencies", InsuranceAgency[].class);
        InsuranceAgency[] agencies = response.getBody();
        ResponseEntity<StatusHouse[]> response1 = restTemplate.getForEntity("http://localhost:8090/api/statuses", StatusHouse[].class);
        StatusHouse[] statuses = response1.getBody();
        ResponseEntity<City[]> response2 = restTemplate.getForEntity("http://localhost:8090/api/cities", City[].class);
        City[] cities = response2.getBody();
        model.addAttribute("agencies", agencies);
        model.addAttribute("cities", cities);
        model.addAttribute("statuses", statuses);
        model.addAttribute("username", principal.getName());
        return "house";
    }

    @RequestMapping(value = "/house", method = RequestMethod.POST)
    public String postHouse(@Valid @ModelAttribute House house,
                            @RequestParam("status") int id_status,
                            @RequestParam("city1") int id_city,
                            @RequestParam("agencyVal") int id_agency,
                            Principal principal,
                            Model model) {
        model.addAttribute("username", principal.getName());
        RestTemplate restTemplate = new RestTemplate();
        modelUser user = restTemplate.getForObject("http://localhost:8090/api/users/user/"+principal.getName(), modelUser.class);
        StatusHouse statusHouse = restTemplate.getForObject("http://localhost:8090/api/statuses/"+id_status, StatusHouse.class);
        City city = restTemplate.getForObject("http://localhost:8090/api/cities/"+id_city, City.class);
        InsuranceAgency insuranceAgency = restTemplate.getForObject("http://localhost:8090/api/agencies/"+id_agency, InsuranceAgency.class);
        house.setCity(city);
        house.setStatusHouse(statusHouse);
        house.setInsuranceAgency(insuranceAgency);
        house.setSells(true);
        restTemplate.postForObject("http://localhost:8090/api/users/"+user.getId()+"/houses", house, House.class);
        return "house";
    }
}
