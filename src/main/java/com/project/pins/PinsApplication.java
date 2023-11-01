package com.project.pins;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PinsApplication {

	public static void main(String[] args) {
	/* 	SpringApplication.run(PinsApplication.class, args); */
		SpringApplication app = new SpringApplication(PinsApplication.class);
		String port = System.getenv("PORT");
		app.setDefaultProperties(Collections.singletonMap("server.port", port == null ? "8080" : port));
		app.run(args);
	}

}
