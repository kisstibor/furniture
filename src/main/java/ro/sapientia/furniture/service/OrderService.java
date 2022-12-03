package ro.sapientia.furniture.service;

import java.util.List;

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
		return this.orderRepository.findOrderById(id);
	}
	
	public Order create(final Order order) {
		return this.orderRepository.saveAndFlush(order);
	}
	
	public Order update(final Order order) {
		return this.orderRepository.saveAndFlush(order);
	}
	
	public void delete(final Long id) {
		this.orderRepository.deleteById(id);
	}
	
}
