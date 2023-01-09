package ro.sapientia.furniture.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.repository.CustomerRepository;
import ro.sapientia.furniture.service.CustomerService;

public class CustomerControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;
    
    private CustomerController customerController;

	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
        customerController = new CustomerController(customerService);
    }

	@Test
	public void testGetAllCustomers() {
	    List<Customer> customers = new ArrayList<>();
	    customers.add(new Customer());
	    customers.add(new Customer());

	    when(customerService.findAllCustomers()).thenReturn(customers);

	    ResponseEntity<List<Customer>> result = customerController.getAllCustomers();

	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(2, result.getBody().size());
	    verify(customerService, times(1)).findAllCustomers();
	}
	
	@Test
	public void testGetCustomerById() {
	    Customer customer = new Customer();
	    customer.setId(1L);

	    when(customerService.findCustomerById(1L)).thenReturn(customer);

	    ResponseEntity<Customer> result = customerController.getCustomerById(1L);

	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(1L, result.getBody().getId().longValue());
	    verify(customerService, times(1)).findCustomerById(1L);
	}
	
	@Test
	public void testAddCustomer() {
	    Customer customer = new Customer();
	    customer.setId(1L);

	    when(customerService.create(customer)).thenReturn(customer);

	    ResponseEntity<Customer> result = customerController.addCustomer(customer);

	    assertEquals(HttpStatus.CREATED, result.getStatusCode());
	    assertEquals(1L, result.getBody().getId().longValue());
	    verify(customerService, times(1)).create(customer);
	}
	
	@Test
	public void testUpdateCustomer() {
	    Customer customer = new Customer();
	    customer.setId(1L);
	    customer.setName("Updated Name");

	    when(customerService.update(1L, customer)).thenReturn(customer);

	    ResponseEntity<Customer> result = customerController.updateCustomer(1L, customer);

	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(1L, result.getBody().getId().longValue());
	    assertEquals("Updated Name", result.getBody().getName());
	    verify(customerService, times(1)).update(1L, customer);
	}
	
	@Test
	public void testDeleteCustomerById() {
	    ResponseEntity<?> result = customerController.deleteCustomerById(1L);

	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    verify(customerService, times(1)).delete(1L);
	}
}
