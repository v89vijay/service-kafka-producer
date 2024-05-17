package com.java.kafka.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.java.kafka.pack")
public class ServiceKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceKafkaProducerApplication.class, args);
	}

}
