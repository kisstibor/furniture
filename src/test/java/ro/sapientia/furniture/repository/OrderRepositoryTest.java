package ro.sapientia.furniture.repository;



import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import ro.sapientia.furniture.model.OrderEntity;
import ro.sapientia.furniture.model.OrderStatus;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class OrderRepositoryTest {
	
	@Autowired
	private OrderRepository orderRepository;
	
	private OrderEntity order;
	
	@BeforeEach
	public void init() {
		order = new OrderEntity(1l,LocalDate.now(),LocalDate.now().plusDays(12),781.90,OrderStatus.ORDERED);
		order = orderRepository.saveAndFlush(order);
	}
	
	
	@Test
	public void findAll() {	
		List<OrderEntity> result = orderRepository.findAll();
		assertEquals(1,result.size());
	}
	
	@Test
	public void testfindByIdShouldReturnObjectWithTheGivenId() {
		assertEquals(order.getId(),orderRepository.findById(order.getId()).get().getId());
	}
	
	@Test
	public void testCreateShouldSaveNewObject() {
		final OrderEntity order1 = new OrderEntity(10l, LocalDate.now(),LocalDate.now().plusDays(132),1980.90,OrderStatus.ORDERED);
		orderRepository.saveAndFlush(order1);
		
		assertEquals(2,orderRepository.findAll().size());
	}
	
	@Test
	public void testUpdateShouldUpdateObject() {
		
		order.setPrice(431.12);
		order = orderRepository.saveAndFlush(order);
		
		final double newPrice = orderRepository.findById(order.getId()).get().getPrice();
		
		assertEquals(431.12,newPrice,2);
	}
	
	@Test
	public void testDeleteShouldRemoveObject() {		
		orderRepository.delete(order);
		
		
		assertEquals(0,orderRepository.findAll().size());
	}
	
	@AfterEach
	public void clean() {
		orderRepository.deleteAll();
	}

}
