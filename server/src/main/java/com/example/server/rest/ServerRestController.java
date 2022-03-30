package com.example.server.rest;

import com.example.server.dto.ServerDTO;
import com.example.server.entity.Server;
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
	public ResponseEntity<ServerDTO> addClientOnServer(@RequestBody Server server) {

		server.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		Server server1 = service.saveClient(server);

		return new ResponseEntity<>(ServerDTO.toServerDTO(server1), HttpStatus.OK);
	}


	@GetMapping("/all")
	public ResponseEntity<List<ServerDTO>> findallServer() {
		List<Server> all = service.findAll();
		return new ResponseEntity<>(all.stream().map(ServerDTO::toServerDTO).collect(Collectors.toList()), HttpStatus.OK);
	}
}
