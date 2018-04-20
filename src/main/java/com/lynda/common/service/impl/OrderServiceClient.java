package com.lynda.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.lynda.common.service.OrderService;

public class OrderServiceClient {

	@Qualifier("orderService2")
	@Autowired
	private OrderService orderService;
	
	public void showOrderStatus() {
		System.out.println("Order status is PROCESSING - ID: " + orderService.getOrder("1234").getOrderId());
	}
	
}
