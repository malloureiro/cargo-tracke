package com.lynda.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.lynda.common.data.repository.CustomerRepository;
import com.lynda.common.data.repository.InventoryItemRepository;
import com.lynda.common.data.repository.SalesOrderRepository;
import com.lynda.common.service.InventoryService;
import com.lynda.common.service.OrderService;
import com.lynda.common.service.impl.InventoryServiceImpl;
import com.lynda.common.service.impl.OrderServiceImpl;

@Configuration
//@Import(DataConfig.class)
@PropertySource("classpath:/application-${spring.profiles.active}.properties")
public class AppConfig {

	@Value("${greeting.text}")
	private String greetingText;
	
	@Value("${greeting.preamble}")
	private String greetingPreamble;
	
	@Bean
	public Worker worker() {
		return new Worker(greetingPreamble, greetingText);
	}
	
	/*@Bean
	@Profile("dev")
	public Worker workerForDev() {
		return new Worker("Hello", greetingText);
	}
	
	@Bean
	@Profile("prod")
	public Worker workerForProd() {
		return new Worker("Greetings", greetingText);
	}*/
	
	@Autowired
	public CustomerRepository customerRepository;
	
	@Autowired
	public SalesOrderRepository salesOrderRepository;
	
	@Autowired
	public InventoryItemRepository inventoryItemRepository;
	
	@Bean
	public OrderService orderService(InventoryService inventoryService, CustomerRepository customerRepository, SalesOrderRepository salesOrderRepository) {
		return new OrderServiceImpl(inventoryService, customerRepository, salesOrderRepository);
	}
	
	@Bean
	public InventoryService inventoryService(InventoryItemRepository inventoryItemRepository) {
		return new InventoryServiceImpl(inventoryItemRepository);
	}
	
	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationContext context = new AnnotationConfigApplicationContext("com.lynda.common.config");
		
		OrderService orderService = context.getBean(OrderService.class);
		System.out.println(orderService == null ? "IS NULL" : "IS OK");

		Worker worker = context.getBean(Worker.class);
		worker.execute();
		// Neste caso, o método execute está printando a mensagem "Hello
		// Mariana", sendo o texto "Mariana" adicionado como uma variável de
		// ambiente. No Spring, as variáveis de ambiente tem prescedência em
		// relação aos arquivos de configuração e é por este motivo que o texto
		// está sendo sobrescrito aqui.
	}
	
	public class Worker {
		
		private String preamble;
		private String text;
		
		public Worker(String preamble, String text) {
			this.preamble = preamble;
			this.text = text;
		}
		
		public void execute() {
			System.out.println(preamble + " " + text);
		}
		
	}
	
}
