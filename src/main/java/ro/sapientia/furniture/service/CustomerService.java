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
		List<Customer> customers = new ArrayList<Customer>();
		if (this.customerRepository.findAll().size() == 0) {
			throw new RuntimeException("No element was found!");
		} else {
			customers = this.customerRepository.findAll();
		}
		return customers;
	}

	public Customer findCustomerById(final Long id) {
		return this.customerRepository.findCustomerById(id);
	}

	public Customer create(Customer customer) {
		try {
			this.customerRepository.saveAndFlush(customer);
		} catch (Exception e) {
			System.out.println("Something happend with the create " + e.getMessage());
		}
		return customer;
	}

	public Customer update(Long id, Customer customer) {
		try {
			customer.setId(id);
			this.customerRepository.saveAndFlush(customer);
		} catch (Exception e) {
			System.out.println("Something happend with the update " + e.getMessage());
		}
		return customer;
	}

	public void delete(Long id) {
		this.customerRepository.deleteById(id);
	}

}