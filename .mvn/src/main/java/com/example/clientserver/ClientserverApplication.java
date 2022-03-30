package com.example.clientserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ClientserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClientserverApplication.class, args);
	}
}




