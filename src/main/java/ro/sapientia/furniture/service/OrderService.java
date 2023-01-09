package ro.sapientia.furniture.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.model.Order;
import ro.sapientia.furniture.repository.OrderRepository;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository ;
	
	public OrderService(final OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	
	public List<Order> findAllOrder(){
		return this.orderRepository.findAll();
	}
	
	public Order findeOrderById(final Long id) {
		return this.orderRepository.findById(id).orElseThrow(
				()->new RuntimeException("User with the given id [ " + id + " ] is not found!"));
	}
	
	public Order create(final Order order) {
		try {
			return this.orderRepository.saveAndFlush(order);
		}
		catch(Exception ex) {
			System.out.println("Erro occurred when saving new entity.  With the next error: " + ex.getLocalizedMessage());
			return null;
		}
		
		
	}
	
	public Order update(final Order order) {
		try {
			return this.orderRepository.saveAndFlush(order);
		}
		catch(Exception ex) {
			System.out.println("Erro occurred when updating entity with id [ "+ order.getId()+" ].  With the next error: " + ex.getLocalizedMessage());
			return null;
		}
	}
	
	public void delete(final Long id) {
		try {
			this.orderRepository.deleteById(id);
		}
		catch(Exception ex) {
			System.out.println("Erro occurred when deleting entity with id [ "+ id +" ].  With the next error: " + ex.getLocalizedMessage());
		}
	}
	
}
