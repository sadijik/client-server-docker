package com.example.server.service;

import com.example.server.entity.Server;
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

	public Server saveClient (Server server){
		return  serverRepository.save(server);
	}

	public  List<Server> findAll() {
		Iterable<Server> all = serverRepository.findAll();
		List<Server> list =new ArrayList<>();
		all.forEach(list::add);
		return list;
	}
}
