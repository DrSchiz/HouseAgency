package com.example.springmodels.controllers;

import com.example.springmodels.models.House;
import com.example.springmodels.models.modelUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class LoggedUserController {
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String getLoggedUser(@PathVariable String username, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        modelUser user = restTemplate.getForObject("http://localhost:8090/api/users/user/"+username, modelUser.class);
        model.addAttribute("user", user);
        return "loggedUser";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public String putLoggerUser(@RequestParam(name = "name") String name, Model model, @PathVariable String username) {
        RestTemplate restTemplate = new RestTemplate();
        modelUser _user = restTemplate.getForObject("http://localhost:8090/api/users/user/"+username, modelUser.class);
        _user.setUsername(name);
        restTemplate.put("http://localhost:8090/api/users/"+_user.getId(), _user, modelUser.class);
        return "login";
    }
}
