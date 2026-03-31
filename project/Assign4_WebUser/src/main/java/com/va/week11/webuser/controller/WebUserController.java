package com.va.week11.webuser.controller;

import com.va.week11.webuser.entity.WebUserEntity;
import com.va.week11.webuser.repository.WebUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class WebUserController {

    @Autowired
    private WebUserRepository webUserRepository;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", webUserRepository.findAll());
        return "users";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("webUser", new WebUserEntity());
        return "addUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute WebUserEntity webUser, Model model) {
        webUserRepository.save(webUser);
        model.addAttribute("message", "User '" + webUser.getUsername() + "' added successfully!");
        model.addAttribute("webUser", new WebUserEntity());
        return "addUser";
    }
}
