package com.example.eurekacommoninfra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaCommoninfraApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaCommoninfraApplication.class, args);
	}

}
