package ro.sapientia.furniture.service;

import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import ro.sapientia.furniture.mock.DatabaseMock;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.model.Stock;
import ro.sapientia.furniture.repository.ManufacturerLocationRepository;
import ro.sapientia.furniture.util.StatusMessage;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static ro.sapientia.furniture.mock.DatabaseMock.manufacturerListWithOneManufacturer;
import static ro.sapientia.furniture.mock.DatabaseMock.manufacturerLocationsWithOneManufacturer;

public class ManufacturerLocationServiceTest {

    @Mock
    private ManufacturerLocationRepository repositoryMock;

    @Autowired
    private ManufacturerLocationService service;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(ManufacturerLocationRepository.class);
        service = new ManufacturerLocationService(repositoryMock);
    }

    @Test
    public void testFindAllManufacturerLocations_emptyListShouldSucceed() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<ManufacturerLocation> manufacturerLocationList = service.findAllManufacturerLocations();
        Assertions.assertEquals(0, manufacturerLocationList.size());
    }

    @Test
    public void testFindAllManufacturerLocations_oneShouldSucceed() {
        when(repositoryMock.findAll()).thenReturn(DatabaseMock.manufacturerLocationsWithOneManufacturer);
        DatabaseMock.manufacturerLocationsWithOneManufacturer = service.findAllManufacturerLocations();
        Assertions.assertEquals(1, DatabaseMock.manufacturerLocationsWithOneManufacturer.size());
    }

    @Test
    public void testFindManufacturerLocationByIdShouldSucceed() {
        when(repositoryMock.findManufacturerLocationById(anyLong())).thenReturn(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0));
        Assertions.assertDoesNotThrow(() -> {
            ManufacturerLocation manufacturerLocation = service.findManufacturerLocationById(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0).getId());

            assertNotNull(manufacturerLocation);
            Assertions.assertEquals(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0), manufacturerLocation);
        });
    }

    @Test
    public void testFindManufacturerLocationByIdShouldFail() {
        when(repositoryMock.findManufacturerLocationById(anyLong())).thenReturn(null);
        NotFoundException thrownException = Assertions.assertThrows(NotFoundException.class, () ->{
            service.findManufacturerLocationById(1L);
        });
        Assertions.assertEquals(
                StatusMessage.NOT_FOUND,
                thrownException.getMessage()
        );
    }

    @Test
    public void testCreateManufacturerLocationShouldSucceed() {
        when(repositoryMock.saveAndFlush(any(ManufacturerLocation.class))).thenReturn(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0));
        ManufacturerLocation manufacturerLocation = service.create(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0));

        assertNotNull(manufacturerLocation.getManufacturer());
        assertNotNull(manufacturerLocation.getStocks());
        assertNotNull(manufacturerLocation.getSchedules());
    }

    @Test
    public void testUpdateManufacturerLocationShouldSucceed() {
        ManufacturerLocation manufacturerLocation = DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0);
        manufacturerLocation.setName("modifiedManufacturerLocation");

        when(repositoryMock.saveAndFlush(any(ManufacturerLocation.class))).thenReturn(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0));
        ManufacturerLocation manufacturerLocation2 = service.update(manufacturerLocation);

        Assertions.assertEquals(manufacturerLocation2.getName(), manufacturerLocation.getName());
    }

    @Test
    public void testDeleteManufacturerLocationShouldSucceed() {
        when(repositoryMock.findManufacturerLocationById(anyLong())).thenReturn(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0));
        Assertions.assertDoesNotThrow(() -> {
            service.delete(manufacturerLocationsWithOneManufacturer.get(0).getId());
        });

        verify(repositoryMock, times(1)).deleteById(manufacturerLocationsWithOneManufacturer.get(0).getId());
    }

}
