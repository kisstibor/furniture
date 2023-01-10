package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import ro.sapientia.furniture.model.OrderEntity;
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
		final List<OrderEntity> returnedData = new ArrayList<>();
		returnedData.add(createDefaultOrder());
		returnedData.add(new OrderEntity(32l,LocalDate.now(),LocalDate.now().plusDays(23),999.9,OrderStatus.ORDERED));
		when(orderRepository.findAll()).thenReturn(returnedData);
		
		assertEquals(returnedData.size(),orderService.findAllOrder().size());
		
	}
	
	@Test
	public void testfindOrderShouldFindTheOrderWithTheGivenId() {
		final OrderEntity order = createDefaultOrder();
		when(orderRepository.findById(ID)).thenReturn(Optional.of(order));
		
		assertEquals(ID,orderService.findOrderById(ID).getId());
	}
	
	@Test
	public void testFindOrderThrowExceptionIfOrderNotFound() {
		final OrderEntity order = createDefaultOrder();
		when(orderRepository.findById(1l)).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class,()->orderService.findOrderById(ID));
	}
	
	@Test
	public void testCreateShouldReturnSavedEntity() {
		final OrderEntity order = createDefaultOrder();
		when(orderRepository.saveAndFlush(order)).thenReturn(order);
		
		assertEquals(order.getId(),orderService.create(order).getId());
	}
	
	@Test
	public void testCreateOrderShouldThrowExceptionWhenErrorOccured() {
		final OrderEntity order = createDefaultOrder();
		when(orderRepository.saveAndFlush(order)).thenThrow(new RuntimeException());
		
		
		assertNull(orderService.create(order));
	}
	
	@Test
	public void testUpdateShouldUpdatePriceOfTheEntity() {
		final OrderEntity order = createDefaultOrder();
		order.setPrice(NEW_PRICE);
		when(orderRepository.saveAndFlush(order)).thenReturn(order);
		
		assertEquals(NEW_PRICE,orderService.update(order).getPrice());
	}
	
	@Test
	public void testUpdateOrderShouldThrowExceptionWhenErrorOccured() {
		final OrderEntity order = createDefaultOrder();
		when(orderRepository.saveAndFlush(order)).thenThrow(new RuntimeException());
		
		assertNull(orderService.update(order));
	}
	
	
	@Test
	public void testDeleteOrderShouldReturnTrueDeleteEntity() {
		
		doNothing().when(orderRepository).deleteById(ID);
		
		assertTrue(orderService.delete(ID));
	}
	
	@Test
	public void testDeleteOrderShouldThrowExceptionWhenErrorOccured() {
		
		doThrow(new RuntimeException()).when(orderRepository).deleteById(ID);
		
		assertFalse(orderService.delete(ID));
	}
	
	
	
	private OrderEntity createDefaultOrder() {
		return new OrderEntity(ID,LocalDate.now(),LocalDate.now().plusDays(10),324.1,OrderStatus.PREAPARING);
	}
	
}
