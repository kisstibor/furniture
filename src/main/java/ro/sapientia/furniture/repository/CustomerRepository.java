package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sapientia.furniture.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findCustomerById(Long id);
	
}
