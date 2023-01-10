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

import ro.sapientia.furniture.model.Customer;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class CustomerRepositoryTest {
	@Autowired
	private CustomerRepository customerRepository;

	private Customer customer;

	@BeforeEach
	public void init() {
		customer = new Customer(1l,"Test Name", "0123456789", "test@email.com");
		customer = customerRepository.saveAndFlush(customer);
	}
	
	@Test
	public void findAll() {	
		List<Customer> result = customerRepository.findAll();
		assertEquals(1,result.size());
	}

	@Test
	public void testfindByIdShouldReturnObjectWithTheGivenId() {
		assertEquals(customer.getId(),customerRepository.findById(customer.getId()).get().getId());
	}

	@Test
	public void testCreateShouldSaveNewObject() {
		final Customer customer1 = new Customer(1l,"Test Name", "0123456789", "test@email.com");
		customerRepository.saveAndFlush(customer1);

		assertEquals(2,customerRepository.findAll().size());
	}

	@Test
	public void testUpdateShouldUpdateObject() {

		customer.setPhone("1212121212");
		customer = customerRepository.saveAndFlush(customer);

		final String newPhone = customerRepository.findById(customer.getId()).get().getPhone();

		assertEquals("1212121212",newPhone);
	}

	@Test
	public void testDeleteShouldRemoveObject() {		
		customerRepository.delete(customer);


		assertEquals(0,customerRepository.findAll().size());
	}
	
	@AfterEach
	public void clean() {
		customerRepository.deleteAll();
	}
}
