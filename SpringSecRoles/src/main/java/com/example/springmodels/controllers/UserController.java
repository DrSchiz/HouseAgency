package com.example.springmodels.controllers;

import com.example.springmodels.models.Feedback;
import com.example.springmodels.models.modelUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getUsers(@RequestParam(name = "search", defaultValue = "") String search, Model model, Principal principal) {
        String username = principal.getName();
        RestTemplate restTemplate = new RestTemplate();
        if (search.equals("")) {
            ResponseEntity<modelUser[]> response = restTemplate.getForEntity("http://localhost:8090/api/users", modelUser[].class);
            modelUser[] users = response.getBody();
            model.addAttribute("users", users);
        } else {
            modelUser users = restTemplate.getForObject("http://localhost:8090/api/users/user/"+search, modelUser.class);
            model.addAttribute("users", users);
        }
        model.addAttribute("username", username);
        return "users";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String postUsers(@RequestParam(name = "search", defaultValue = "") String search, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("search", search);
        return "redirect:/users/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(Model model, Principal principal, @PathVariable int id) {
        String username = principal.getName();
        RestTemplate restTemplate = new RestTemplate();
        modelUser user = restTemplate.getForObject("http://localhost:8090/api/users/"+id, modelUser.class);
        model.addAttribute("user", user);
        model.addAttribute("username", username);
        return "user";
    }

    @RequestMapping(value = "/{id}/feedback", method = RequestMethod.GET)
    public String getFeedback(Model model, Principal principal, @PathVariable int id, @RequestParam(name = "feedback", defaultValue = "") String feedbackText) {
        String username = principal.getName();
        model.addAttribute("username", username);
        model.addAttribute("id", id);
        model.addAttribute("feedback", feedbackText);
        return "feedback";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @RequestMapping(value = "/{id}/feedback", method = RequestMethod.POST)
    public String postFeedback(Model model, Principal principal, @RequestParam(name = "feedback", defaultValue = "") String feedbackText, @PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        Feedback feedback = new Feedback();
        feedback.setFeedbackText(feedbackText);
        restTemplate.postForObject("http://localhost:8090/api/users/"+id+"/feedbacks", feedback, Feedback.class);
        ResponseEntity<modelUser[]> response = restTemplate.getForEntity("http://localhost:8090/api/users", modelUser[].class);
        modelUser[] users = response.getBody();
        model.addAttribute("users", users);
        return "users";
    }
}
