package ro.sapientia.furniture.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import ro.sapientia.furniture.model.Order;

@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class OrderRepositoryTest {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Test
	public void findAll() {	
		List<Order> result = orderRepository.findAll();
		assertEquals(0,result.size());
	}

}
