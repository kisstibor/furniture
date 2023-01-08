package ro.sapientia.furniture.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.repository.CustomerRepository;
import ro.sapientia.furniture.service.CustomerService;

@WebMvcTest(controllers = CustomerController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CustomerControllerTest {
	private static final Long ID =10L;
	
	private CustomerRepository customerRepository;

	private CustomerService customerService;

	@BeforeEach
	public void setUp() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
	}

	@Test
	public void testFindAllShouldReturnEmptyListWhenNoEntitiesExist() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
		when(customerRepository.findAll()).thenReturn(Collections.emptyList());

		assertEquals(0,customerService.findAllCustomers().size());
	}
	
	@Test
	public void testFindAllSouldReturnAllEntitiesThatExists() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
		final List<Customer> returnedData = new ArrayList<>();
		returnedData.add(new Customer("Test Name", "0147852369", "test@email.com"));
		when(customerRepository.findAll()).thenReturn(returnedData);

		assertEquals(returnedData.size(),customerService.findAllCustomers());

	}

	@Test
	public void testfindCustomerShouldFindTheCustomerWithTheGivenId() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
		final Customer customer = createDefaultCustomer();
		when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));

		assertEquals(ID,customerService.findCustomerById(1l).getId());
	}

	@Test
	public void testFindCustomerThrowExceptionIfCustomerNotFound() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
		final Customer customer = createDefaultCustomer();
		when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class,customerService.findCustomerById(1l));
	}

	@Test
	public void testCreateShouldReturnSavedEntity() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
		final Customer customer = createDefaultCustomer();
		when(customerRepository.saveAndFlush(customer)).thenReturn(customer);

		assertEquals(customer.getId(),customerService.create(customer).getId());
	}

	@Test
	public void testCreateCustomerShouldThrowExceptionWhenErrorOccured() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
		final Customer customer = createDefaultCustomer();
		when(customerRepository.saveAndFlush(createDefaultCustomer())).thenThrow(new RuntimeException());

		assertThrows(RuntimeException.class,customerService.create(customer));
	}

	@Test
	public void testUpdateNameShouldUpdateTheEntity() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
		final Customer customer = createDefaultCustomer();
		customer.setName("New Name");
		when(customerRepository.saveAndFlush(customer)).thenReturn(customer);

		assertEquals("New Name",customerService.update(customer.getId(), customer).getName());
	}

	@Test
	public void testUpdatePhoneShouldThrowExceptionWhenErrorOccured() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
		final Customer customer = createDefaultCustomer();
		when(customerRepository.saveAndFlush(createDefaultCustomer())).thenThrow(new RuntimeException());

		assertThrows(RuntimeException.class,customerService.update(ID, customer));
	}


	@Test
	public void testDeleteCustomerShouldThrowExceptionWhenErrorOccured() {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerService(customerRepository);
		when(customerRepository.deleteById(ID)).thenThrow(new RuntimeException());

		assertThrows(RuntimeException.class,customerService.delete(ID));
	}
	
	private Customer createDefaultCustomer() {
		return new Customer(ID,"Default Name", "0236541789", "email@test.com");
	}
	
}
