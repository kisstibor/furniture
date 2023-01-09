package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.repository.CustomerRepository;
import ro.sapientia.furniture.service.CustomerService;

public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void testFindAllCustomers() {
        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setName("John Smith");

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setName("Jane Smith");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Customer> customers = customerService.findAllCustomers();

        assertEquals(2, customers.size());
        assertTrue(customers.contains(c1));
        assertTrue(customers.contains(c2));
    }

    @Test
    public void testFindAllCustomers_emptyList() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList());

        try {
            customerService.findAllCustomers();
        } catch (Exception e) {
            assertEquals("No element was found!", e.getMessage());
        }
    }

    @Test
    public void testFindCustomerById() {
        Customer c = new Customer();
        c.setId(1L);
        c.setName("John Smith");

        when(customerRepository.findCustomerById(1L)).thenReturn(c);

        Customer customer = customerService.findCustomerById(1L);

        assertEquals(c, customer);
    }

    @Test
    public void testCreate() {
        Customer c = new Customer();
        c.setId(1L);
        c.setName("John Smith");

        when(customerRepository.saveAndFlush(c)).thenReturn(c);

        Customer customer = customerService.create(c);

        assertEquals(c, customer);
    }

    @Test
    public void testUpdate() {
        Customer c = new Customer();
        c.setId(1L);
        c.setName("John Smith");

        when(customerRepository.saveAndFlush(c)).thenReturn(c);

        Customer customer = customerService.update(1L, c);

        assertEquals(1L, (long) customer.getId());
        assertEquals("John Smith", customer.getName());
    }

    @Test
	public void testDelete() {
	    Customer c = new Customer();
	    c.setId(1L);
	    c.setName("John Smith");
	
	    when(customerRepository.findCustomerById(1L)).thenReturn(c);
	
	    customerService.delete(1L);
	
	    when(customerRepository.findCustomerById(1L)).thenReturn(null);
	    Customer customer = customerRepository.findCustomerById(1L);
	
	    assertEquals(null, customer);
	}
}
