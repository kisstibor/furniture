package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sapientia.furniture.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity,Long>{

	OrderEntity findOrderById(Long id);
	
}
