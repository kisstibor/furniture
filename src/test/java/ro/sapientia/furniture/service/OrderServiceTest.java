package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import ro.sapientia.furniture.model.Order;
import ro.sapientia.furniture.model.OrderStatus;
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
		when(orderRepository.findAll()).thenReturn(Collections.emptyList());
		
		assertEquals(0,orderService.findAllOrder().size());
	}
	
	@Test
	public void testFindAllSouldReturnAllEntitiesThatExists() {
		final List<Order> returnedData = new ArrayList<>();
		returnedData.add(createDefaultOrder());
		returnedData.add(new Order(32l,LocalDate.now(),LocalDate.now().plusDays(23),999.9,OrderStatus.ORDERED));
		when(orderRepository.findAll()).thenReturn(returnedData);
		
		assertEquals(returnedData.size(),orderService.findAllOrder().size());
		
	}
	
	@Test
	public void testfindOrderShouldFindTheOrderWithTheGivenId() {
		final Order order = createDefaultOrder();
		when(orderRepository.findById(ID)).thenReturn(Optional.of(order));
		
		assertEquals(ID,orderService.findeOrderById(ID).getId());
	}
	
	@Test
	public void testFindOrderThrowExceptionIfOrderNotFound() {
		final Order order = createDefaultOrder();
		when(orderRepository.findById(1l)).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class,()->orderService.findeOrderById(ID));
	}
	
	@Test
	public void testCreateShouldReturnSavedEntity() {
		final Order order = createDefaultOrder();
		when(orderRepository.saveAndFlush(order)).thenReturn(order);
		
		assertEquals(order.getId(),orderService.create(order).getId());
	}
	
	@Test
	public void testCreateOrderShouldThrowExceptionWhenErrorOccured() {
		final Order order = createDefaultOrder();
		when(orderRepository.saveAndFlush(createDefaultOrder())).thenThrow(new RuntimeException());
		
		
		assertNull(orderService.create(order));
	}
	
	@Test
	public void testUpdateShouldUpdatePriceOfTheEntity() {
		final Order order = createDefaultOrder();
		order.setPrice(NEW_PRICE);
		when(orderRepository.saveAndFlush(order)).thenReturn(order);
		
		assertEquals(NEW_PRICE,orderService.update(order).getPrice());
	}
	
	@Test
	public void testUpdateOrderShouldThrowExceptionWhenErrorOccured() {
		final Order order = createDefaultOrder();
		when(orderRepository.saveAndFlush(createDefaultOrder())).thenThrow(new RuntimeException());
		
		assertNull(orderService.update(order));
	}
	
	
	@Test
	@Disabled
	public void testDeleteOrderShouldThrowExceptionWhenErrorOccured() {
		
		//when(orderRepository.deleteById(ID)).thenThrows(new RuntimeException());
		
		assertThrows(RuntimeException.class,()->{orderService.delete(ID);});
	}
	
	
	
	private Order createDefaultOrder() {
		return new Order(ID,LocalDate.now(),LocalDate.now().plusDays(10),324.1,OrderStatus.PREAPARING);
	}
	
}
