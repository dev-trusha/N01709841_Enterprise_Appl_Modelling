package com.va.week11.webuser.controller;

import com.va.week11.webuser.entity.WebUserEntity;
import com.va.week11.webuser.repository.WebUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class WebUserRestController {

    @Autowired
    private WebUserRepository webUserRepository;

    @GetMapping
    public List<WebUserEntity> getAllUsers() {
        return webUserRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebUserEntity> getUserById(@PathVariable Long id) {
        return webUserRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
