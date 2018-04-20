package com.lynda.common.service.impl;

import java.util.Map;

import com.lynda.common.domain.Customer;
import com.lynda.common.domain.Order;
import com.lynda.common.service.OrderService;

public class OrderServiceImpl3 implements OrderService {

	public Order createOrder(Customer customer, Map<String, Long> items) {
		// TODO Auto-generated method stub
		return null;
	}

	public Order createOrder(String customerId, Map<String, Long> items) {
		// TODO Auto-generated method stub
		return null;
	}

	public Order getOrder(String orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		System.out.println(":: OrderServiceImpl3 - order id: " + orderId);
		return order;
	}

}
