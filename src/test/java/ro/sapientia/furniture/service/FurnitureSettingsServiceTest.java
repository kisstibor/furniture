package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.model.SettingsServiceBody;
import ro.sapientia.furniture.repository.FurnitureSettingsServiceRepository;

public class FurnitureSettingsServiceTest {
	
	private FurnitureSettingsServiceRepository repositoryMock;
	
	private FurnitureSettingsService service;
	
	@BeforeEach
	public void setUp() {
		repositoryMock = mock(FurnitureSettingsServiceRepository.class);
		service = new FurnitureSettingsService(repositoryMock);
	}
	
	@Test
	public void testFindAllSettingsServiceBodies_emptyList() {
		when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
		final List<SettingsServiceBody> settingsServiceBodies =  service.findAllSettingsServiceBody();
		
		assertEquals(0, settingsServiceBodies.size());
	}
	
	@Test
	public void testFindAllSettingsServiceBodies_null() {
		when(repositoryMock.findAll()).thenReturn(null);
		final List<SettingsServiceBody> settingsServiceBodies =  service.findAllSettingsServiceBody();
		
		assertNull(settingsServiceBodies);
	}
}
