package ro.sapientia.furniture.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.repository.CustomerRepository;
import ro.sapientia.furniture.service.CustomerService;

public class CustomerServiceTests {


    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllCustomers_twoElement() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Name Test");
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Name Test2");
        when(repository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = service.findAllCustomers();
        assertEquals(2, customers.size());
        assertTrue(customers.contains(customer1));
        assertTrue(customers.contains(customer2));
    }

    @Test
    public void testFindAllCustomers_oneElement() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Test Name");
        when(repository.findAll()).thenReturn(Arrays.asList(customer1));

        List<Customer> customers = service.findAllCustomers();
        assertEquals(1, customers.size());
        assertTrue(customers.contains(customer1));
    }

    @Test
	public void testFindAllCustomers_emptyList() {
		final List<Customer> customers =  service.findAllCustomers();
		when(repository.findAll()).thenReturn(Arrays.asList());

		assertEquals(0, customers.size());
	}

    @Test
	public void testFindAllCustomers_exception() {
		when(repository.findAll()).thenThrow(new RuntimeException());

		assertNull(service.findAllCustomers());
	}

    @Test
    public void testFindCustomerById() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test Name");
        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = service.findCustomerById(1L);
        assertNotNull(result);
        assertEquals(customer, result);
    }

    @Test
	public void testFindCustomerById_notFound() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class,() -> service.findCustomerById(1L));
	}

    @Test
    public void testCreate() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test Name");
        when(repository.saveAndFlush(customer)).thenReturn(customer);

        Customer result = service.create(customer);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(customer, result);
    }

    @Test
    public void testCreate_exception() {		
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test Name");
        when(repository.saveAndFlush(customer)).thenThrow(new RuntimeException());

        assertNull(service.create(customer));
    }

    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test Name");
        when(repository.findCustomerById(1L)).thenReturn(customer);
        when(repository.saveAndFlush(customer)).thenReturn(customer);

        Customer result = service.update(customer);

        assertEquals(1L, result.getId().longValue());
        assertEquals("Test Name", result.getName());
    }  
 
    @Test
    public void testUpdate_failure() {
        Long id = 1L;
        Customer customer = new Customer();
        when(repository.saveAndFlush(customer)).thenThrow(new RuntimeException());

        assertNull(service.update(customer));
    }

    @Test
    public void testDelete_success() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        boolean result = service.delete(id);

        assertTrue(result);
    }

    @Test
    public void testDelete_failure() {
        Long id = 1L;
        doThrow(new RuntimeException()).when(repository).deleteById(id);

        assertFalse(service.delete(id));
    }
}
