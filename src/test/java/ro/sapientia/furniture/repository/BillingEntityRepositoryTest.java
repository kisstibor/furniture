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

import ro.sapientia.furniture.model.BillingEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class BillingEntityRepositoryTest {

	@Autowired
	private BillingEntityRepository billingEntityRepository;

	private BillingEntity billingEntity;

	@BeforeEach
	public void init() {
		billingEntity = new BillingEntity(1L, 1L, "Jhon Doe", 0);
		billingEntity = billingEntityRepository.saveAndFlush(billingEntity);
	}


	@Test
	public void findAll() {	
		List<BillingEntity> result = billingEntityRepository.findAll();
		assertEquals(1,result.size());
	}

	@Test
	public void testfindByIdShouldReturnObjectWithTheGivenId() {
		assertEquals(billingEntity.getId(),billingEntityRepository.findById(billingEntity.getId()).get().getId());
	}

	@Test
	public void testCreateShouldSaveNewObject() {
		final BillingEntity billingEntity = new BillingEntity(1L, 1L, "Jhon Doe", 0);
		billingEntityRepository.saveAndFlush(billingEntity);

		assertEquals(2,billingEntityRepository.findAll().size());
	}

	@Test
	public void testUpdateShouldUpdateObject() {

		billingEntity.setDeposit(500.5);
		billingEntity = billingEntityRepository.saveAndFlush(billingEntity);

		final double newDeposit = billingEntityRepository.findById(billingEntity.getId()).get().getDeposit();

		assertEquals(500.5,newDeposit,2);
	}

	@Test
	public void testDeleteShouldRemoveObject() {		
		billingEntityRepository.delete(billingEntity);


		assertEquals(0,billingEntityRepository.findAll().size());
	}

	@AfterEach
	public void clean() {
		billingEntityRepository.deleteAll();
	}

}