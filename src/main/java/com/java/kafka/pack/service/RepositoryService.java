package com.java.kafka.pack.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.kafka.pack.entity.PurchaseOrder;
import com.java.kafka.pack.exception.ProductException;
import com.java.kafka.pack.model.OrderRequestDto;
import com.java.kafka.pack.repository.ProductRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class RepositoryService{
	
	private Map<String,Integer>orderMap ;
	
	@PostConstruct
	public void setMap() {
		orderMap= new LinkedHashMap<>();
		orderMap.put("103", 103);
		orderMap.put("104", 104);
		orderMap.put("105", 105);
	}
	
	@Autowired
	ProductRepository productRepository;
	
	public List<PurchaseOrder> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Transactional(rollbackOn = ProductException.class)
	public String saveNewOrder(PurchaseOrder purchaseOrder) throws ProductException{
		productRepository.save(purchaseOrder);
		System.out.println("order inserted in table successfully ...."+purchaseOrder.getId());
		
		System.out.println("checking available bal ");
		checkBal(purchaseOrder);
		
		return "new order created ...";
	}
	
	private boolean checkBal(PurchaseOrder purchaseOrder) throws ProductException{
		Integer prise = orderMap.get(purchaseOrder.getUserId().toString());
		if(prise<purchaseOrder.getPrice()) {
			throw new ProductException("insufficiant bal");
		}
		return true;
	}
}
