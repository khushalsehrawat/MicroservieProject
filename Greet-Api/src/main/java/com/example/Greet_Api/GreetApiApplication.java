package com.example.Greet_Api;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GreetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetApiApplication.class, args);
	}

}

