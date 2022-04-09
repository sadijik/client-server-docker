package com.example.client;

import com.example.client.entity.LogingRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ClientApplicationTests {


	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RestTemplate restTemplate;


	private MockRestServiceServer mockServer;

	@BeforeEach
	public void init() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}


	@Test
	public void AddServerTest() throws Exception {

		//given
		LogingRequest logingRequest = new LogingRequest();
		logingRequest.setText("текстовое сообщение");

		mockServer.expect(ExpectedCount.once(),
						requestTo(new URI("http://localhost:8511/server/add")))
				.andExpect(method(HttpMethod.POST))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(objectMapper.writeValueAsString(logingRequest)));

		//when
		ResultActions result = mvc.perform(post("/log/create")
						.content(objectMapper.writeValueAsString(logingRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print());

		//Then
		result.andExpect(status().isOk());

		//Проверка что RestTemplate был вызван
		mockServer.verify();
	}

	@Test
	public void AddServerNull() throws Exception {

		//given
		LogingRequest logingRequest = new LogingRequest();
		logingRequest.setText(null);

		mockServer.expect(ExpectedCount.once(),
						requestTo(new URI("http://localhost:8511/server/add")))
				.andExpect(method(HttpMethod.POST))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(objectMapper.writeValueAsString(logingRequest)));

		//when
		ResultActions result = mvc.perform(post("/log/create")
						.content(objectMapper.writeValueAsString(logingRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print());


		//Then
		result.andExpect(status().isOk());

		//Проверка что RestTemplate был вызван
		mockServer.verify();
	}

	@Test
	public void AddServerBadRequest() throws Exception {
		//given
		LogingRequest logingRequest = new LogingRequest();

		mockServer.expect(ExpectedCount.once(),
						requestTo(new URI("http://localhost:8511/server/add")))
				.andExpect(method(HttpMethod.POST))
				.andRespond(withStatus(HttpStatus.BAD_REQUEST)
						.contentType(MediaType.APPLICATION_JSON)
						.body(objectMapper.writeValueAsString(logingRequest)));

		//when
		ResultActions result = mvc.perform(post("/log/create")
						.content(objectMapper.writeValueAsString(logingRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print());

		//Then
		result.andExpect(status().isBadRequest());

		assertEquals("Bad Request", result.andReturn().getResponse().getContentAsString());

		//Проверка что RestTemplate был вызван
		mockServer.verify();
	}
}

