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

import ro.sapientia.furniture.model.Shipment;
import ro.sapientia.furniture.repository.ShipmentRepository;

public class ShipmentServiceTest {

    @InjectMocks
    private ShipmentService service;

    @Mock
    private ShipmentRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllShipments_twoElement() {
        Shipment shipment1 = new Shipment();
        shipment1.setId(1L);
        shipment1.setStreet("Szezam 1");
        Shipment shipment2 = new Shipment();
        shipment2.setId(2L);
        shipment2.setStreet("Szezam 2");
        when(repository.findAll()).thenReturn(Arrays.asList(shipment1, shipment2));

        List<Shipment> shipments = service.findAllShipments();
        assertEquals(2, shipments.size());
        assertTrue(shipments.contains(shipment1));
        assertTrue(shipments.contains(shipment2));
    }
    
    @Test
    public void testFindAllShipments_oneElement() {
        Shipment shipment1 = new Shipment();
        shipment1.setId(1L);
        shipment1.setStreet("Szezam 1");
        when(repository.findAll()).thenReturn(Arrays.asList(shipment1));

        List<Shipment> shipments = service.findAllShipments();
        assertEquals(1, shipments.size());
        assertTrue(shipments.contains(shipment1));
    }
    
    @Test
	public void testFindAllShipments_emptyList() {
		final List<Shipment> shipments =  service.findAllShipments();
		when(repository.findAll()).thenReturn(Arrays.asList());
		
		assertEquals(0, shipments.size());
	}
    
    @Test
	public void testFindAllShipments_exception() {
		when(repository.findAll()).thenThrow(new RuntimeException());
		
		assertNull(service.findAllShipments());
	}

    @Test
    public void testFindShipmentById() {
        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setStreet("Szezam 1");
        when(repository.findById(1L)).thenReturn(Optional.of(shipment));

        Shipment result = service.findShipmentById(1L);
        assertNotNull(result);
        assertEquals(shipment, result);
    }
    
    @Test
	public void testFindShipmentById_notFound() {
		when(repository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class,() -> service.findShipmentById(1L));
	}

    @Test
    public void testCreate() {
        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setStreet("Szezam 1");
        when(repository.saveAndFlush(shipment)).thenReturn(shipment);

        Shipment result = service.create(shipment);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(shipment, result);
    }
    
    @Test
    public void testCreate_exception() {		
        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setStreet("Szezam 1");
        when(repository.saveAndFlush(shipment)).thenThrow(new RuntimeException());

        assertNull(service.create(shipment));
    }

    @Test
    public void testUpdate() {
        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setStreet("Szezam 1");
        when(repository.findShipmentById(1L)).thenReturn(shipment);
        when(repository.saveAndFlush(shipment)).thenReturn(shipment);
        
        Shipment result = service.update(1L, shipment);

        assertEquals(1L, result.getId().longValue());
        assertEquals("Szezam 1", result.getStreet());
    }
    
    @Test
    public void testUpdate_failure() {
        Long id = 1L;
        Shipment shipment = new Shipment();
        when(repository.findShipmentById(id)).thenThrow(new RuntimeException());
        
        assertNull(service.update(id, shipment));
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