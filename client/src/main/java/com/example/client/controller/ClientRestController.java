package com.example.client.controller;


import com.example.client.entity.Server;
import com.example.client.properties.ClientConfigPropoerties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@RestController
@RequestMapping("/client")
public class ClientRestController {
@Value("${client.url}")
private String url;


	private final RestTemplate restTemplate;

	@Autowired
	public ClientRestController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;

	}

	@GetMapping()
	public String greeting() {
		return "серваер On";
	}

	@PostMapping("/add_server")
	public Server addServer(@RequestBody Server server) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Server> entity = new HttpEntity<>(server, headers);


		return restTemplate.exchange(url, HttpMethod.POST, entity, Server.class).getBody();


	}


}
