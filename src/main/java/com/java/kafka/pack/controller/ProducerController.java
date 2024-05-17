package com.java.kafka.pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.kafka.pack.entity.PurchaseOrder;
import com.java.kafka.pack.exception.ProductException;
import com.java.kafka.pack.model.OrderModel;
import com.java.kafka.pack.service.ProducerPublisherService;
import com.java.kafka.pack.service.RepositoryService;

@RestController
@RequestMapping("/producer-app")
public class ProducerController {

	@Autowired
	ProducerPublisherService producerPublisherService;

	@Autowired
	RepositoryService repositoryService;

	@PostMapping("/publish")
	public ResponseEntity<String> publishkafkaMsg(@RequestBody OrderModel orderModel) {
		try {
			producerPublisherService.sendEncodeMessageObjectOnTopic(orderModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@GetMapping("/orders")
	public ResponseEntity<List<PurchaseOrder>> getPurchaseOrder() {
		List<PurchaseOrder> li = repositoryService.getAllProducts();
		return new ResponseEntity<>(li, HttpStatus.OK);
	}

	@PostMapping("/new-order")
	public ResponseEntity<String> saveNewPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
		String status;
		try {
			status = repositoryService.saveNewOrder(purchaseOrder);
		} catch (ProductException exception) {
			status = "order reverted";
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
