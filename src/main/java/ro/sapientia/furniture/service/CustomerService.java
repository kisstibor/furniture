package ro.sapientia.furniture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.repository.CustomerRepository;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;
	
	public CustomerService(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public List<Customer> findAllCustomers() {
		try {
			return this.customerRepository.findAll();
		} catch(RuntimeException e) {
			return null;
		}
	}
 
	public Customer findCustomerById(final Long id) {
		return this.customerRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Element with the given id " + id + " was not found"));
	}

	public Customer create(Customer customer) {
		try {
			return this.customerRepository.saveAndFlush(customer);
		} catch (RuntimeException e) {
			System.out.println("Something happend with the during create "  + e.getMessage());
			return null;
		}
	}

	public Customer update(Customer customer) {
		try {
			return this.customerRepository.saveAndFlush(customer);
		} catch (RuntimeException e) {
			System.out.println("Something happend with the update item with id: " + customer.getId() + ">>>"  + e.getMessage());
			return null;
		}
	} 

	public boolean delete(Long id) {
		try {
			this.customerRepository.deleteById(id);
			return true;
		} catch(RuntimeException e) {
			System.out.println("Error occured while deleting item with id: " + id + ">>>" + e.getMessage());
			return false;
		}
	}

}