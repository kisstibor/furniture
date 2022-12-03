package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sapientia.furniture.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

	Order findOrderById(Long id); 
	
}
