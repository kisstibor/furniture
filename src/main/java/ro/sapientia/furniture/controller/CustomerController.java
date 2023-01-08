package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.service.CustomerService;

@RestController
@RequestMapping("/customer")
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

		@PostMapping("/update/{id}")
		public ResponseEntity<Customer> updateCustomer(@RequestBody Long id, Customer customer){
			final Customer persistenceCustomer = customerService.update(id, customer);
			return new ResponseEntity<>(persistenceCustomer,HttpStatus.OK);
		}

		@GetMapping("delete/{id}")
		public ResponseEntity<?> deleteCustomerById(@PathVariable("id") Long id){
			customerService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	
}
