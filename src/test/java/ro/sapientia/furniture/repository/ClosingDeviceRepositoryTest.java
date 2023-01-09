package ro.sapientia.furniture.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import ro.sapientia.furniture.model.ClosingDevice;
import ro.sapientia.furniture.service.ClosingDeviceService;



@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ClosingDeviceRepositoryTest {
	@Autowired
	private ClosingDeviceRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@AfterEach
	private void tearDown() {
		entityManager.clear();
	}

	@Test
	public void findAllListBeEmpty() {
		List<ClosingDevice> closingDeviceList = repository.findAll();
		assertEquals(0, closingDeviceList.size());
	}

	@Test
	public void findAllList() {
		ClosingDevice closingDevice = new ClosingDevice();
		closingDevice.setHeight(10);
		closingDevice.setWidth(5);
		closingDevice.setDepth(3);

		closingDevice = entityManager.persistAndFlush(closingDevice);

		List<ClosingDevice> closingDeviceList = repository.findAll();

		assertEquals(1, closingDeviceList.size());
	}

	@Test
	public void findById() {
		ClosingDevice closingDevice = new ClosingDevice();
		closingDevice.setHeight(12);
		closingDevice.setWidth(5);
		closingDevice.setDepth(3);

		closingDevice = repository.saveAndFlush(closingDevice);

		assertEquals(closingDevice, repository.findById(closingDevice.getId()).get());
	}

	@Test
	public void createTest() {
		final ClosingDevice closingDevice = new ClosingDevice();
		final long myId = 1;
		closingDevice.setId(myId);
		closingDevice.setHeight(20);
		closingDevice.setWidth(10);
		closingDevice.setDepth(5);

		ClosingDevice newClosingDevice = repository.saveAndFlush(closingDevice);

        assertEquals(20, newClosingDevice.getHeight());
	}

	@Test
	public void updateTest() {
		final ClosingDevice closingDevice = new ClosingDevice();
		final long myId = 1;
		closingDevice.setId(myId);
		closingDevice.setHeight(20);
		closingDevice.setWidth(10);
		closingDevice.setDepth(5);

		ClosingDevice newClosingDevice = repository.saveAndFlush(closingDevice);
		newClosingDevice.setHeight(45);

		ClosingDevice updatedClosingDevice = repository.saveAndFlush(newClosingDevice);

        assertEquals(45, updatedClosingDevice.getHeight());
	}
}
