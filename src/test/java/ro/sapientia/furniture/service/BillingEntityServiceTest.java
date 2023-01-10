package ro.sapientia.furniture.service;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.sapientia.furniture.model.BillingEntity;
import ro.sapientia.furniture.repository.BillingEntityRepository;

public class BillingEntityServiceTest {

    @InjectMocks
    private BillingEntityService billingEntityService;

    @Mock
    private BillingEntityRepository billingEntityRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllBillingEntitys_twoElement() {
        BillingEntity billingEntity1 = new BillingEntity(1L, 1L, "Jhon Doe", 0);
        BillingEntity billingEntity2 = new BillingEntity(2L, 2L, "Jhon Doe", 0);
        when(billingEntityRepository.findAll()).thenReturn(Arrays.asList(billingEntity1, billingEntity2));

        List<BillingEntity> BillingEntitys = billingEntityService.findAllBillingEntities();
        assertEquals(2, BillingEntitys.size());
        assertTrue(BillingEntitys.contains(billingEntity1));
        assertTrue(BillingEntitys.contains(billingEntity2));
    }
    
    @Test
    public void testFindAllBillingEntitys_oneElement() {
        BillingEntity billingEntity1 = new BillingEntity(1L, 1L, "Jhon Doe", 0);
        when(billingEntityRepository.findAll()).thenReturn(Arrays.asList(billingEntity1));

        List<BillingEntity> BillingEntitys = billingEntityService.findAllBillingEntities();
        assertEquals(1, BillingEntitys.size());
        assertTrue(BillingEntitys.contains(billingEntity1));
    }
    
    @Test
	public void testFindAllBillingEntitys_emptyList() {
		final List<BillingEntity> billingEntities =  billingEntityService.findAllBillingEntities();
		when(billingEntityRepository.findAll()).thenReturn(Arrays.asList());
		
		assertEquals(0, billingEntities.size());
	}
    
    @Test
	public void testFindAllBillingEntitys_exception() {
		when(billingEntityRepository.findAll()).thenThrow(new RuntimeException());
		
		assertNull(billingEntityService.findAllBillingEntities());
	}

    @Test
    public void testFindBillingEntityById() {
        BillingEntity billingEntity1 = new BillingEntity(1L, 1L, "Jhon Doe", 0);
        when(billingEntityRepository.findById(1L)).thenReturn(Optional.of(billingEntity1));

        BillingEntity result = billingEntityService.findBillingEntityById(1L);
        assertNotNull(result);
        assertEquals(billingEntity1, result);
    }
    
    @Test
	public void testFindBillingEntityById_notFound() {
		when(billingEntityRepository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class,() -> billingEntityService.findBillingEntityById(1L));
	}

    @Test
    public void testCreate() {
        BillingEntity billingEntity1 = new BillingEntity(1L, 1L, "Jhon Doe", 0);
        when(billingEntityRepository.saveAndFlush(billingEntity1)).thenReturn(billingEntity1);

        BillingEntity result = billingEntityService.create(billingEntity1);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(billingEntity1, result);
    }
    
    @Test
    public void testCreate_exception() {		
        BillingEntity billingEntity1 = new BillingEntity(1L, 1L, "Jhon Doe", 0);
        when(billingEntityRepository.saveAndFlush(billingEntity1)).thenThrow(new RuntimeException());

        assertNull(billingEntityService.create(billingEntity1));
    }

    @Test
    public void testUpdate() {
        BillingEntity billingEntity1 = new BillingEntity(1L, 1L, "Jhon Doe", 0);
        when(billingEntityRepository.saveAndFlush(billingEntity1)).thenReturn(billingEntity1);
        
        BillingEntity result = billingEntityService.update(billingEntity1);

        assertEquals("Jhon Doe", result.getCustomerName());
    }
    
    @Test
    public void testUpdate_failure() {
        Long id = 1L;
        BillingEntity BillingEntity = new BillingEntity();
        when(billingEntityRepository.saveAndFlush(BillingEntity)).thenThrow(new RuntimeException());
        
        assertNull(billingEntityService.update(BillingEntity));
    }
    
    @Test
    public void testDelete_success() {
        Long id = 1L;
        when(billingEntityRepository.existsById(id)).thenReturn(true);

        boolean result = billingEntityService.delete(id);

        assertTrue(result);
    }

    @Test
    public void testDelete_failure() {
        Long id = 1L;
        doThrow(new RuntimeException()).when(billingEntityRepository).deleteById(id);

        assertFalse(billingEntityService.delete(id));
    }
}