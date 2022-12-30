package ro.sapientia.furniture.service;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;

import ro.sapientia.furniture.repository.OrderRepository;

public class OrderServiceTest {
	
	private OrderRepository orderRepository;
	
	private OrderService orderService;
	
	@BeforeAll
	public void setUp() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
	}
}
