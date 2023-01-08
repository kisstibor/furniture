package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import ro.sapientia.furniture.model.Order;
import ro.sapientia.furniture.repository.OrderRepository;

public class OrderServiceTest {
	
	private static final Long ID =10L;
	
	private static final Double NEW_PRICE = 420.99;
	
	private OrderRepository orderRepository;
	
	private OrderService orderService;
	
	@BeforeEach
	public void setUp() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
	}
	
	@Test
	public void testFindAllShouldReturnEmptyListWhenNoEntitiesExist() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
		when(orderRepository.findAll()).thenReturn(Collections.emptyList());
		
		assertEquals(0,orderService.findAllOrder().size());
	}
	
	@Test
	public void testFindAllSouldReturnAllEntitiesThatExists() {
		orderRepository = mock(OrderRepository.class);
	orderService = new OrderService(orderRepository);
		final List<Order> returnedData = new ArrayList<>();
		returnedData.add(createDefaultOrder());
		returnedData.add(new Order(32,LocalDate.now(),LocalDate.now().plusDays(23),999.99));
		when(orderRepository.findAll()).thenReturn(returnedData);
		
		assertEquals(returnedData.size(),orderService.findAllOrder());
		
	}
	
	@Test
	public void testfindOrderShouldFindTheOrderWithTheGivenId() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
		final Order order = createDefaultOrder();
		when(orderRepository.findById(ID)).thenReturn(Optional.of(order));
		
		assertEquals(ID,orderService.findeOrderById(1l).getId());
	}
	
	@Test
	public void testFindOrderThrowExceptionIfOrderNotFound() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
		final Order order = createDefaultOrder();
		when(orderRepository.findById(1l)).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class,orderService.findeOrderById(1l));
	}
	
	@Test
	public void testCreateShouldReturnSavedEntity() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
		final Order order = createDefaultOrder();
		when(orderRepository.saveAndFlush(order)).thenReturn(order);
		
		assertEquals(order.getId(),orderService.create(order).getId());
	}
	
	@Test
	public void testCreateOrderShouldThrowExceptionWhenErrorOccured() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
		final Order order = createDefaultOrder();
		when(orderRepository.saveAndFlush(createDefaultOrder())).thenThrow(new RuntimeException());
		
		assertThrows(RuntimeException.class,orderService.create(order));
	}
	
	@Test
	public void testUpdateShouldUpdatePriceOfTheEntity() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
		final Order order = createDefaultOrder();
		order.setPrice(NEW_PRICE);
		when(orderRepository.saveAndFlush(order)).thenReturn(order);
		
		assertEquals(NEW_PRICE,orderService.update(order).getPrice());
	}
	
	@Test
	public void testUpdateOrderShouldThrowExceptionWhenErrorOccured() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
		final Order order = createDefaultOrder();
		when(orderRepository.saveAndFlush(createDefaultOrder())).thenThrow(new RuntimeException());
		
		assertThrows(RuntimeException.class,orderService.update(order));
	}
	
	
	@Test
	public void testDeleteOrderShouldThrowExceptionWhenErrorOccured() {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService(orderRepository);
		when(orderRepository.deleteById(ID)).thenThrow(new RuntimeException());
		
		assertThrows(RuntimeException.class,orderService.delete(ID));
	}
	
	
	
	private Order createDefaultOrder() {
		return new Order(ID,LocalDate.now(),LocalDate.now().plusDays(10),324.1);
	}
	
}
