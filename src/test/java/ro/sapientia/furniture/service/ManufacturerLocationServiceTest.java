package ro.sapientia.furniture.service;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.model.Stock;
import ro.sapientia.furniture.repository.ManufacturerLocationRepository;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ManufacturerLocationServiceTest {

    private ManufacturerLocationRepository repositoryMock;

    private ManufacturerLocationService service;

    private List<Manufacturer> manufacturers = new ArrayList<>();
    private List<ManufacturerLocation> locations = new ArrayList<>();
    private List<Stock> stocks = new ArrayList<>();
    private List<Schedule> schedules = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(ManufacturerLocationRepository.class);
        service = new ManufacturerLocationService(repositoryMock);
        manufacturers.add( new Manufacturer(
                1L,
                "manufacturer1"
        ));
        manufacturers.add( new Manufacturer(
                2L,
                "manufacturer2"
        ));
        locations.add( new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturers.get(0),
                new HashSet<>(),
                new HashSet<>()
        ));
        locations.add( new ManufacturerLocation(
                2L,
                "manufacturerLocation2",
                "address2",
                manufacturers.get(1),
                new HashSet<>(),
                new HashSet<>()
        ));
    }

    @Test
    public void testFindAllManufacturerLocations_emptyListShouldSucceed() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<ManufacturerLocation> manufacturerLocationList = service.findAllManufacturerLocations();
        assertEquals(0, manufacturerLocationList.size());
    }

    @Test
    public void testFindAllManufacturerLocations_twoShouldSucceed() {
        when(repositoryMock.findAll()).thenReturn(locations);
        locations = service.findAllManufacturerLocations();
        assertEquals(2, locations.size());
    }

    @Test
    public void testFindManufacturerLocationByIdShouldSucceed() {
        // when
        when(repositoryMock.findManufacturerLocationById(anyLong())).thenReturn(locations.get(0));
        ManufacturerLocation manufacturerLocation = service.findManufacturerLocationById(locations.get(0).getId());

        // then
        assertNotNull(manufacturerLocation);
        assertEquals(locations.get(0), manufacturerLocation);
    }

    @Test
    public void testFindManufacturerLocationByIdShouldFail() {
        // when
        when(repositoryMock.findManufacturerLocationById(anyLong())).thenReturn(null);
        ManufacturerLocation nonExistingManufacturerLocation = service.findManufacturerLocationById(1L);

        // then
        assertNull(nonExistingManufacturerLocation);
    }

    @Test
    public void testCreateManufacturerLocationShouldSucceed() {
        // when
        when(repositoryMock.saveAndFlush(any(ManufacturerLocation.class))).thenReturn(locations.get(0));
        ManufacturerLocation manufacturerLocation = service.create(locations.get(0));

        // then
        assertNotNull(manufacturerLocation.getManufacturer());
        assertNotNull(manufacturerLocation.getStocks());
        assertNotNull(manufacturerLocation.getSchedules());
    }

    @Test
    public void testUpdateManufacturerLocationShouldSucceed() {
        // given
        ManufacturerLocation manufacturerLocation = locations.get(0);
        manufacturerLocation.setName("modifiedManufacturerLocation");

        // when
        when(repositoryMock.saveAndFlush(any(ManufacturerLocation.class))).thenReturn(locations.get(0));
        ManufacturerLocation manufacturerLocation2 = service.update(manufacturerLocation);

        // then
        assertEquals(manufacturerLocation2.getName(), manufacturerLocation.getName());
    }

    @Test
    public void testDeleteManufacturerLocationShouldSucceed() {
        // when
        doNothing().when(repositoryMock).deleteById(anyLong());
        service.delete(1L);

        // then
        verify(repositoryMock, times(1)).deleteById(1L);
    }

}
