package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ro.sapientia.furniture.model.ClosingDevice;
import ro.sapientia.furniture.repository.ClosingDeviceRepository;

public class ClosingDeviceServiceTest {
	private ClosingDeviceRepository repositoryMock;
	private ClosingDeviceService service;
	private ClosingDevice closingDevice;

	@BeforeEach
	public void setUp() {
		repositoryMock = mock(ClosingDeviceRepository.class);
		service = new ClosingDeviceService(repositoryMock);
		closingDevice = new ClosingDevice();
		closingDevice.setId(1L);
		closingDevice.setWidth(8);
		closingDevice.setHeight(12);
		closingDevice.setDepth(3);
	}

	@Test
	public void testFindAllClosingDevices_emptyList() {
		when(repositoryMock.findAll()).thenReturn(Collections.emptyList());

		final List<ClosingDevice> closingDevices =  service.findAllClosingDevices();

		assertEquals(0, closingDevices.size());
	}

	@Test
	public void testFindAllClosingDevices_null() {
		when(repositoryMock.findAll()).thenReturn(null);

		final List<ClosingDevice> closingDevices =  service.findAllClosingDevices();

		assertNull(closingDevices);
	}

	@Test
	public void testFindAllClosingDevices() {

		when(repositoryMock.findAll()).thenReturn(List.of(closingDevice));

		final List<ClosingDevice> closingDevices =  service.findAllClosingDevices();

		assertEquals(closingDevices.size(), 1);
	}

	@Test
	public void testDelete() {
		final ClosingDevice closingDevice = new ClosingDevice();
		final long myId = 1;
		closingDevice.setId(myId);
		closingDevice.setHeight(20);
		closingDevice.setWidth(10);
		closingDevice.setDepth(5);

		//when
		when(repositoryMock.saveAndFlush(any(ClosingDevice.class))).thenReturn(closingDevice);
		ClosingDevice newClosingDevice = service.create(closingDevice);
		given(repositoryMock.findById(1L)).willReturn(Optional.of(newClosingDevice));

        willDoNothing().given(repositoryMock).deleteById(newClosingDevice.getId());

        service.delete(newClosingDevice.getId());

        verify(repositoryMock, times(1)).deleteById(myId);
	}

	@Test
	public void testCreateClosingDevice() {
		//given
		final ClosingDevice closingDevice = new ClosingDevice();
		final long myId = 1;
		closingDevice.setId(myId);
		closingDevice.setHeight(20);
		closingDevice.setWidth(10);
		closingDevice.setDepth(5);

		//when
		when(repositoryMock.saveAndFlush(any(ClosingDevice.class))).thenReturn(closingDevice);
		ClosingDevice newClosingDevice = service.create(closingDevice);

		//then
		assertEquals(myId, (long) newClosingDevice.getId());
	}

	@Test
	public void testNotCreateClosingDevice() {
		//when
		when(repositoryMock.saveAndFlush(any())).thenReturn(null);
		ClosingDevice newClosingDevice = service.create(closingDevice);

		//then
		assertNull(newClosingDevice);
	}

	@Test
	public void testUpdatedClosingDevice(){
		final ClosingDevice closingDevice = new ClosingDevice();
		final long myId = 1;
		closingDevice.setId(myId);
		closingDevice.setHeight(20);
		closingDevice.setWidth(10);
		closingDevice.setDepth(5);

		//when
		given(repositoryMock.saveAndFlush(any(ClosingDevice.class))).willReturn(closingDevice);
		ClosingDevice newClosingDevice = service.create(closingDevice);

		given(repositoryMock.findById(1L)).willReturn(Optional.of(newClosingDevice));
		newClosingDevice.setHeight(45);

		ClosingDevice updatedClosingDevice = service.update(newClosingDevice);

        assertEquals(45, updatedClosingDevice.getHeight());
    }

	@Test
	public void testFindByIdClosingDevice() {
		given(repositoryMock.findById(1L)).willReturn(Optional.of(closingDevice));

		ClosingDevice closingD = service.findClosingDeviceById(closingDevice.getId());

		assertEquals((long) closingD.getId(), 1L);
	}
}
