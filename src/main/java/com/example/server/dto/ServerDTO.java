package com.example.server.dto;

import com.example.server.entity.Server;
import lombok.Data;

@Data
public class ServerDTO {
	private Long id;
	private String text;

	public static ServerDTO toServerDTO(Server server) {
		ServerDTO serverDTO =new ServerDTO();
		serverDTO.setId(server.getId());
		serverDTO.setText(server.getText());
		return serverDTO;
	}
}
