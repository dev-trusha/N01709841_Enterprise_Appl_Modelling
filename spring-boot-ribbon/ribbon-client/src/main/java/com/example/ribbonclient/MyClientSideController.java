package com.example.ribbonclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyClientSideController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/client/frontend")
	public String hi() {
		String randomString = this.restTemplate.getForObject("http://server/backend", String.class);
		return "Server Response :: " + randomString;
	}
}
