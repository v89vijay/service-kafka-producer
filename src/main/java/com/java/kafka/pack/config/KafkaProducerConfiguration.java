package com.java.kafka.pack.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.kafka.pack.service.AppConstants;

@Configuration
public class KafkaProducerConfiguration {

	
	@Bean
	public NewTopic createNewTopic() {
		return new NewTopic(AppConstants.KAFKA_TOPIC_ORDER,1,(short)1);
	}
}
