package com.example.server.service;

import com.example.server.entity.LogingRequest;
import com.example.server.repo.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerService {

	private final ServerRepository serverRepository;

	@Autowired
	public ServerService(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
	}

	public LogingRequest saveClient(LogingRequest logingRequest) {
		return serverRepository.save(logingRequest);
	}

	public List<LogingRequest> findAll() {
		Iterable<LogingRequest> all = serverRepository.findAll();
		List<LogingRequest> list = new ArrayList<>();
		all.forEach(list::add);
		return list;
	}
}
