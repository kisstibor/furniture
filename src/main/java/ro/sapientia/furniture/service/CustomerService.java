package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.repository.CustomerRepository;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;
	
	public CustomerService(final CustomerRepository customerBodyRepository) {
		this.customerRepository = customerBodyRepository;
	}
	
	public List<Customer> findAllCustomers() {
		return this.customerRepository.findAll();
	}

	public Customer findCustomerById(final Long id) {
		return this.customerRepository.findCustomerById(id);
	}

	public Customer create(Customer customerBody) {
		return this.customerRepository.saveAndFlush(customerBody);
	}

	public Customer update(Customer customerBody) {
		return this.customerRepository.saveAndFlush(customerBody);
	}

	public void delete(Long id) {
		this.customerRepository.deleteById(id);
	}

}