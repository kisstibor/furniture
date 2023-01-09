package ro.sapientia.furniture.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import ro.sapientia.furniture.model.Shipment;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class ShipmentRepositoryTest {

	@Autowired
	ShipmentRepository repository;
	
	Shipment shipment = new Shipment(1L, "Szezam u.", "4A", "Tg.Mures", 569785);

	@BeforeEach
	public void init() {
		shipment = repository.saveAndFlush(shipment);
	}
	
	
	@Test
	public void findAll() {	
		List<Shipment> result = repository.findAll();
		assertEquals(1,result.size());
	}
	
	@Test
	public void testfindByIdShouldReturnObjectWithTheGivenId() {
		assertEquals(shipment.getId(),repository.findById(shipment.getId()).get().getId());
	}
	
	@Test
	public void testCreateShouldSaveNewObject() {
		Shipment shipment2 = new Shipment(101L, "Szezam u.", "4B", "Tg.Mures", 569785);
		repository.saveAndFlush(shipment2);
		
		assertEquals(2,repository.findAll().size());
	}
	
	@Test
	public void testUpdateShouldUpdateObject() {
		
		shipment.setStreet("Malom u.");
		shipment = repository.saveAndFlush(shipment);
		
		final String newStreet = repository.findById(shipment.getId()).get().getStreet();
		
		assertEquals("Malom u.",newStreet);
	}
	
	@Test
	public void testDeleteShouldRemoveObject() {		
		repository.delete(shipment);
		
		
		assertEquals(0,repository.findAll().size());
	}
	
	@AfterEach
	public void clean() {
		repository.deleteAll();
	}
}
