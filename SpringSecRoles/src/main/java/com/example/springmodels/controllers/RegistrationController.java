package com.example.springmodels.controllers;


import com.example.springmodels.models.modelUser;
import com.example.springmodels.models.roleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/reg")
public class RegistrationController {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getReg(modelUser user) {
        return "reg";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postReg(@Valid modelUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reg";
        }
        RestTemplate restTemplate = new RestTemplate();
        modelUser _user = restTemplate.getForObject("http://localhost:8090/api/users/user/" + user.getUsername(), modelUser.class);
        if (_user != null) {
            return "reg";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(roleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        restTemplate.postForObject("http://localhost:8090/api/users", user, modelUser.class);
        return "redirect:/login";
    }
}
