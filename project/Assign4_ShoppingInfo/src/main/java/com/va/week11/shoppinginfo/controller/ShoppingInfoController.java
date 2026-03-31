package com.va.week11.shoppinginfo.controller;

import com.va.week11.shoppinginfo.client.WebUserClient;
import com.va.week11.shoppinginfo.model.WebUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingInfoController {

    @Autowired
    private WebUserClient webUserClient;

    /**
     * GET /api/shopping/{loginId}
     * Fetches WebUser fields by login ID (WebUserEntity.id) from the WebUser service.
     * Returns data in REST/JSON format.
     */
    @GetMapping("/{loginId}")
    public ResponseEntity<Map<String, Object>> getShoppingInfoByLoginId(@PathVariable Long loginId) {
        WebUserModel user = webUserClient.getUserById(loginId);

        Map<String, Object> response = new HashMap<>();
        response.put("loginId", user.getId());
        response.put("username", user.getUsername());
        response.put("stateofuser", user.getStateofuser());
        response.put("service", "ShoppingInfo");

        return ResponseEntity.ok(response);
    }
}
