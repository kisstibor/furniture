package ro.sapientia.furniture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.service.CustomerService;

@SpringBootApplication
public class FurnitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureApplication.class, args);
		
	}
	
	

}
