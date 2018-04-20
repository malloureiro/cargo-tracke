package com.lynda.common.config2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lynda.common.service.OrderService;
import com.lynda.common.service.impl.OrderServiceClient;
import com.lynda.common.service.impl.OrderServiceImpl2;
import com.lynda.common.service.impl.OrderServiceImpl3;

@Configuration
public class AppConfig {

	@Qualifier("orderService2")
	@Bean
	public OrderService createOrderService2() {
		return new OrderServiceImpl2();
	}
	
	@Qualifier("orderService3")
	@Bean
	public OrderService createOrderService3() {
		return new OrderServiceImpl3();
	}
	
	@Bean
	public OrderServiceClient createOrderServiceClient() {
		return new OrderServiceClient();
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		OrderServiceClient orderServiceClient = context.getBean(OrderServiceClient.class);
		orderServiceClient.showOrderStatus();
	}
}
