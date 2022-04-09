package com.example.client.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ConfigurationProperties(prefix = "client")
@Getter
@Setter
@PropertySource("classpath:client.properties")
public class ClientConfigPropoerties {
	private String url;
}
