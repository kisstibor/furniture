package ro.sapientia.furniture.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.model.OrderEntity;
import ro.sapientia.furniture.repository.OrderRepository;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository ;
	
	public OrderService(final OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	
	public List<OrderEntity> findAllOrder(){
		return this.orderRepository.findAll();
	}
	
	public OrderEntity findOrderById(final Long id) {
		return this.orderRepository.findById(id).orElseThrow(
				()->new RuntimeException("User with the given id [ " + id + " ] is not found!"));
	}
	
	public OrderEntity create(final OrderEntity order) {
		try {
			return this.orderRepository.saveAndFlush(order);
		}

		catch(RuntimeException ex) {
			System.out.println("Error occurred when saving new entity.  With the next error: " + ex.getLocalizedMessage());
			return null;
		}
		
		
	}
	
	public OrderEntity update(final OrderEntity order) {
		try {
			return this.orderRepository.saveAndFlush(order);
		}
		catch(RuntimeException ex) {
			System.out.println("Error occurred when updating entity with id [ "+ order.getId()+" ].  With the next error: " + ex.getLocalizedMessage());
			return null;
		}
	}
	
	public boolean delete(final Long id) {
		try {
			this.orderRepository.deleteById(id);
			return true;
		}

		catch(RuntimeException ex) {
			System.out.println("Error occurred when deleting entity with id [ "+ id +" ].  With the next error: " + ex.getLocalizedMessage());
			return false;

		}
	}
	
}
