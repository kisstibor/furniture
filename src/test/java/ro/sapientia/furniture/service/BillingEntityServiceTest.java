package ro.sapientia.furniture.service;

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

import ro.sapientia.furniture.model.BillingEntity;
import ro.sapientia.furniture.repository.BillingEntityRepository;

public class BillingEntityServiceTest {
	
	private static final Long ID = 10L;
	private static final Long CREDITCARD = 20L;
	private static final String CUSTOMERNAME = "Jhon Doe";
	private static final int DEPOSIT = 200;
	
	private static final String NEW_CUSTOMERNAME = "Minden Aron";
	
	private BillingEntityRepository billingEntityRepository;
	private BillingEntityService billingEntityService;
	
	@BeforeEach
	public void setup() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
	}
	
	@Test
	public void testFindAllShouldRetrunEmptyListWhenNoEntitiesExist() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
		when(billingEntityRepository.findAll()).thenReturn(Collections.emptyList());
		
		assertEquals(0, billingEntityService.findAllBillingEntities().size());
	}
	
	@Test
	public void testFindAllShouldReturnAllEntitiesThatExists() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
		final List<BillingEntity> returnedData = new ArrayList<>();
		returnedData.add(createDefaultBillingEntity());
		when(billingEntityRepository.findAll()).thenReturn(returnedData);
		
		assertEquals(returnedData.size(), billingEntityService.findAllBillingEntities());
	}

	@Test
	public void testFindBillingEntityShouldFindTheBillingEntityWithTheGivenId() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
		final BillingEntity billingEntity = createDefaultBillingEntity();
		when(billingEntityRepository.findById(ID)).thenReturn(Optional.of(billingEntity));
		
		assertEquals(ID, billingEntityService.findBillingEntityById(1l).getId());
	}
	
	@Test
	public void testFindBillingEntityThrowExceptionIfBillingEntityNotFound() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
		final BillingEntity billingEntity = createDefaultBillingEntity();
		when(billingEntityRepository.findById(1l)).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class, billingEntityService.findBillingEntityById(1l));
	}
	
	@Test 
	public void testCreateShouldReturnSavedEntity() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
		final BillingEntity billingEntity = createDefaultBillingEntity();
		when(billingEntityRepository.saveAndFlush(billingEntity)).thenReturn(billingEntity);
		
		assertEquals(billingEntity.getId(), billingEntityService.create(billingEntity).getId());
	}
	
	@Test
	public void testCreateShouldThrowExceptionWhenErrorOccured() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
		final BillingEntity billingEntity = createDefaultBillingEntity();
		when(billingEntityRepository.saveAndFlush(billingEntity)).thenThrow(new RuntimeException());
		
		assertThrows(RuntimeException.class, billingEntityService.create(billingEntity));
	}
	
	@Test
	public void testUpdateShouldUpdateTheNameOfTheEntity() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
		final BillingEntity billingEntity = createDefaultBillingEntity();
		billingEntity.setCustomerName(NEW_CUSTOMERNAME);
		when(billingEntityRepository.saveAndFlush(billingEntity)).thenReturn(billingEntity);
		
		assertEquals(NEW_CUSTOMERNAME, billingEntityService.update(billingEntity.getId(), billingEntity).getCustomerName());
	}
	
	@Test
	public void testUpdateShouldThrowxceptionWhenErrorOccured() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
		final BillingEntity billingEntity = createDefaultBillingEntity();
		when(billingEntityRepository.saveAndFlush(billingEntity)).thenThrow(new RuntimeException());
		
		assertThrows(RuntimeException.class, billingEntityService.update(billingEntity.getId(), billingEntity));
	}
	
	@Test
	public void testDeleteShouldThrowExceptionWhenErrorOccured() {
		billingEntityRepository = mock(BillingEntityRepository.class);
		billingEntityService = new BillingEntityService(billingEntityRepository);
		when(billingEntityRepository.deleteById(ID)).thenThrow(new RuntimeException());
		
		assertThrows(RuntimeException.class, billingEntityService.delete(ID));
	}
	
	private BillingEntity createDefaultBillingEntity() {
		return new BillingEntity(ID, CREDITCARD, CUSTOMERNAME, DEPOSIT)
	}
}
