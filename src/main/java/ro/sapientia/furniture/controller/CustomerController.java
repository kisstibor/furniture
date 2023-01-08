package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.service.CustomerService;

public class CustomerController {

	
		private final CustomerService customerService;
		
		public CustomerController(final CustomerService customerService) {
			this.customerService = customerService;
		}
		
		@GetMapping("/all")
		public ResponseEntity<List<Customer>> getAllCustomers(){
			final List<Customer> customers = customerService.findAllCustomers();
			return new ResponseEntity<>(customers,HttpStatus.OK);
		}
		
		@GetMapping("/find/{id}")
		public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
			final Customer customer = customerService.findCustomerById(id);
			return new ResponseEntity<>(customer,HttpStatus.OK);
		}

		@PostMapping("/add")
		public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
			final Customer persistenceCustomer = customerService.create(customer);
			return new ResponseEntity<>(persistenceCustomer,HttpStatus.CREATED);
		}

		@PostMapping("/update")
		public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
			final Customer persistenceCustomer = customerService.update(customer);
			return new ResponseEntity<>(persistenceCustomer,HttpStatus.OK);
		}

		@GetMapping("delete/{id}")
		public ResponseEntity<?> deleteCustomerById(@PathVariable("id") Long id){
			customerService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	
}
