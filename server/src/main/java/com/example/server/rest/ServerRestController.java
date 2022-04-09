package com.example.server.rest;

import com.example.server.dto.LogingRequestDTO;
import com.example.server.entity.LogingRequest;
import com.example.server.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/server")
public class ServerRestController {

	private ServerService service;

	@Autowired
	public ServerRestController(ServerService service) {
		this.service = service;
	}

	@PostMapping("/add")
	public ResponseEntity<LogingRequestDTO> addClientOnServer(@RequestBody LogingRequest logingRequest) {
		logingRequest.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		LogingRequest logingRequest1 = service.saveClient(logingRequest);
		return new ResponseEntity<>(LogingRequestDTO.toServerDTO(logingRequest1), HttpStatus.OK);

	}


	@GetMapping("/all")
	public ResponseEntity<List<LogingRequestDTO>> findallServer() {
		List<LogingRequest> all = service.findAll();
		return new ResponseEntity<>(all.stream().map(LogingRequestDTO::toServerDTO).collect(Collectors.toList()), HttpStatus.OK);
	}
}
