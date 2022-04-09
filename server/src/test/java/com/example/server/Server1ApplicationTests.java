package com.example.server;

import com.example.server.entity.LogingRequest;
import com.example.server.repo.ServerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Server1ApplicationTests {
	@Autowired
	private ServerRepository repository;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@AfterEach
	public void resetDb() {
		repository.deleteAll();

	}

	@Test
	public void addClientOnServerTest() throws Exception {
		//given
		LogingRequest logingRequest = new LogingRequest();
		logingRequest.setText("проверка");
		logingRequest.setId(1L);

		//when
		ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/server/add")
						.content(objectMapper.writeValueAsString(logingRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print());

		//then
		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.text").value("проверка"));

	}

	@Test
	public void allTest() throws Exception {

		//given
		LogingRequest logingRequest = new LogingRequest();
		logingRequest.setText("проверка2");
		logingRequest.setId(2L);

		//when
		repository.save(logingRequest);

		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/server/all"))
				.andDo(print());

		//then
		result.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(logingRequest.getId()))
				.andExpect(jsonPath("$[0].text").value("проверка2"));


	}
}
