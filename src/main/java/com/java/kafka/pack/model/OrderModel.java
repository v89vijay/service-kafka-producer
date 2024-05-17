package com.java.kafka.pack.model;

import java.io.Serializable;

public record OrderModel(Long orderId, String orderName, String status) implements Serializable {}
