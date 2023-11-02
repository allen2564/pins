package com.project.pins;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class PinsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PinsApplication.class);
		String port = System.getenv("PORT");
		String host = System.getenv("HOST");
		app.setDefaultProperties(Collections.singletonMap("server.port", port == null ? "8080" : port));
		app.setDefaultProperties(Collections.singletonMap("server.address", host == null ? "10.0.116.183" : host));
		app.run(args);
	}

}
