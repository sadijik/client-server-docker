package com.example.client.controller;


import com.example.client.entity.LogingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@RestController
@RequestMapping("/log")
public class ClientRestController {
	@Value("${client.url}")
	private String url;


	private final RestTemplate restTemplate;

	@Autowired
	public ClientRestController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;

	}

	@GetMapping()
	public ResponseEntity<String> greeting() {
		return ResponseEntity.ok("серваер On");
	}

	@PostMapping("/create")
	public void addServer(@RequestBody LogingRequest logingRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<LogingRequest> entity = new HttpEntity<>(logingRequest, headers);
		ResponseEntity<LogingRequest> response = restTemplate.exchange(url, HttpMethod.POST, entity, LogingRequest.class);

	}
}
