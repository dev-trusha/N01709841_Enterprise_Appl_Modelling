package com.va.week11.shoppinginfo.client;

import com.va.week11.shoppinginfo.model.WebUserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client that calls the WebUser service (registered in Eureka as "webuser-service").
 * Uses GET /api/users/{id} to fetch a user by login ID.
 */
@FeignClient(name = "webuser-service", url = "http://localhost:8081")
public interface WebUserClient {

    @GetMapping("/api/users/{id}")
    WebUserModel getUserById(@PathVariable("id") Long id);
}
