package com.java.kafka.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.kafka.pack.entity.PurchaseOrder;

@Repository
public interface ProductRepository extends JpaRepository<PurchaseOrder, Integer>{
}
