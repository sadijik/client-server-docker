package com.example.server.dto;

import com.example.server.entity.LogingRequest;
import lombok.Data;

@Data
public class LogingRequestDTO {
	private Long id;
	private String text;

	public static LogingRequestDTO toServerDTO(LogingRequest logingRequest) {
		LogingRequestDTO logingRequestDTO = new LogingRequestDTO();
		logingRequestDTO.setId(logingRequest.getId());
		logingRequestDTO.setText(logingRequest.getText());
		return logingRequestDTO;
	}
}
