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
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
		LogingRequest sever = new LogingRequest();
		sever.setText("проверка");

		mockServer.expect(ExpectedCount.once(),
						requestTo(new URI("http://localhost:8511/server/add")))
				.andExpect(method(HttpMethod.POST))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(objectMapper.writeValueAsString(sever)));

		//when
		ResultActions result = mvc.perform(post("/client/add_server")
				.content(objectMapper.writeValueAsString(sever))
				.contentType(MediaType.APPLICATION_JSON));

		//Then
		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.text").value("проверка"));

	}

	@Test
	public void AddServerNull() throws Exception {

		//given
		LogingRequest sever = new LogingRequest();
		sever.setText(null);

		mockServer.expect(ExpectedCount.once(),
						requestTo(new URI("http://localhost:8511/server/add")))
				.andExpect(method(HttpMethod.POST))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(objectMapper.writeValueAsString(sever)));

		//when
		ResultActions result = mvc.perform(post("/client/add_server")
				.content(objectMapper.writeValueAsString(sever))
				.contentType(MediaType.APPLICATION_JSON));


		//Then
		result.andExpect(jsonPath("$.text").value(sever.getText()));


	}
}


