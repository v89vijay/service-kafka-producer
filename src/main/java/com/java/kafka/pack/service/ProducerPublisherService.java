package com.java.kafka.pack.service;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.kafka.pack.model.OrderModel;

@Service
public class ProducerPublisherService {

	@Autowired
	KafkaTemplate<String, Object> template;
	
	
	public void sendMessageOnTopic(String msg) {
		CompletableFuture<SendResult<String,Object>>feature = template.send(AppConstants.KAFKA_TOPIC_ONE, msg);
		feature.whenComplete((result,exp)->{
			if(exp == null) {
				System.out.println("msg = "+result.getRecordMetadata().offset());
			}else {
				System.err.println("error = "+exp.getMessage());
			}
		});
	}
	
	public void sendMessageObjectOnTopic(OrderModel orderModel) {
	
		CompletableFuture<SendResult<String,Object>>feature = template.send(AppConstants.KAFKA_TOPIC_ORDER, orderModel);
		feature.whenComplete((result,exp)->{
			if(exp == null) {
				System.out.println("published order = "+result.getRecordMetadata().offset());
			}else {
				System.err.println("error = "+exp.getMessage());
			}
		});
	}
	
	public void sendEncodeMessageObjectOnTopic(OrderModel orderModel) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		Encoder enc = Base64.getEncoder();
		String obj1 = mapper.writeValueAsString(orderModel);
		String encMsg = enc.encodeToString(obj1.getBytes());
		
		KafkaProducer<String, Object> pp;
		
		CompletableFuture<SendResult<String,Object>>feature = template.send(AppConstants.KAFKA_TOPIC_ORDER, encMsg);
		feature.whenComplete((result,exp)->{
			if(exp == null) {
				System.out.println("published order = "+result.getRecordMetadata().offset());
			}else {
				System.err.println("error = "+exp.getMessage());
			}
		});
	}
}
