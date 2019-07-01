package com.packsendme.microservice.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicePaymentServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePaymentServerApplication.class, args);
	}
}
