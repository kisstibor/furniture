package ro.sapientia.furniture.service;

import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.repository.ManufacturerRepository;
import ro.sapientia.furniture.util.StatusMessage;

import java.util.*;

import static org.mockito.Mockito.*;
import static ro.sapientia.furniture.mock.DatabaseMock.manufacturerListWithMultipleManufacturers;
import static ro.sapientia.furniture.mock.DatabaseMock.manufacturerListWithOneManufacturer;

public class ManufacturerServiceTest {

    private ManufacturerRepository repositoryMock;

    private ManufacturerService service;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(ManufacturerRepository.class);
        service = new ManufacturerService(repositoryMock);
    }

    @Test
    public void testFindAllManufacturers_null() {
        when(repositoryMock.findAll()).thenReturn(null);
        final List<Manufacturer> manufacturers = service.findAllManufacturers();

        Assertions.assertNull(manufacturers, "Returned value is not \"null\" as expected.");
    }

    @Test
    public void testFindAllManufacturers_emptyList() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<Manufacturer> manufacturers = service.findAllManufacturers();

        Assertions.assertEquals(0, manufacturers.size(), "List size is not \"1\" as expected.");
    }

    @Test
    public void testFindAllManufacturers_listWithOneManufacturer() {
        when(repositoryMock.findAll()).thenReturn(manufacturerListWithOneManufacturer);
        final List<Manufacturer> manufacturers = service.findAllManufacturers();

        Assertions.assertEquals(
                1,
                manufacturers.size(),
                "List size is not \"1\" as expected."
        );
        Assertions.assertEquals(
                manufacturerListWithOneManufacturer,
                manufacturers,
                "Returned value is not the expected list."
        );
    }

    @Test
    public void testFindAllManufacturers_listWithMultipleManufacturers() {
        when(repositoryMock.findAll()).thenReturn(manufacturerListWithMultipleManufacturers);
        final List<Manufacturer> manufacturers = service.findAllManufacturers();

        Assertions.assertEquals(
                manufacturerListWithMultipleManufacturers.size(),
                manufacturers.size(),
                "List size is not \"" + manufacturerListWithMultipleManufacturers.size() + "\" as expected."
        );
        Assertions.assertEquals(
                manufacturerListWithMultipleManufacturers,
                manufacturers,
                "Returned value is not the expected list."
        );
    }

    @Test
    public void testFindManufacturerById_notExistingId() {
        when(repositoryMock.findManufacturerById(1L)).thenReturn(null);

        NotFoundException thrownException = Assertions.assertThrows(NotFoundException.class, () -> {
            service.findManufacturerById(1L);
        }, "NotFoundException was expected.");

        Assertions.assertEquals(
                StatusMessage.NOT_FOUND,
                thrownException.getMessage(),
                "Exception status message is not as expected."
        );
    }

    @Test
    public void testFindManufacturerById_existingId() {
        when(repositoryMock.findManufacturerById(manufacturerListWithOneManufacturer.get(0).getId()))
                .thenReturn(manufacturerListWithOneManufacturer.get(0));

        Assertions.assertDoesNotThrow(() -> {
            Manufacturer manufacturer = service.findManufacturerById(manufacturerListWithOneManufacturer.get(0).getId());

            Assertions.assertEquals(
                    manufacturerListWithOneManufacturer.get(0),
                    manufacturer,
                    "Returned value is not the expected."
            );
        }, "Exception was not expected.");
    }

    @Test
    public void testFindManufacturerById_multipleExistingIds() {
        manufacturerListWithMultipleManufacturers.forEach(manufacturer -> {
            when(repositoryMock.findManufacturerById(manufacturer.getId())).thenReturn(manufacturer);
        });

        manufacturerListWithMultipleManufacturers.forEach(manufacturer -> {
            Assertions.assertDoesNotThrow(() -> {
                Manufacturer manufacturerResult = service.findManufacturerById(manufacturer.getId());

                Assertions.assertEquals(
                        manufacturer,
                        manufacturerResult,
                        "Returned value is not the expected."
                );
            }, "Exception was not expected.");
        });
    }
    
    @Test
    public void testCreateManufacturer() {
        when(repositoryMock.saveAndFlush(manufacturerListWithOneManufacturer.get(0)))
                .thenReturn(manufacturerListWithOneManufacturer.get(0));

        service.create(manufacturerListWithOneManufacturer.get(0));

        verify(repositoryMock, times(1)).saveAndFlush(any());
    }

    @Test
    public void testDeleteManufacturer_notExistingId() {
        when(repositoryMock.findManufacturerById(anyLong())).thenReturn(null);

        NotFoundException thrownException = Assertions.assertThrows(NotFoundException.class, () -> {
            service.delete(anyLong());
        }, "NotFoundException was expected.");

        Assertions.assertEquals(
                StatusMessage.NOT_FOUND,
                thrownException.getMessage(),
                "Exception status message is not as expected."
        );

        verify(repositoryMock, times(0)).deleteById(anyLong());
    }

    @Test
    public void testDeleteManufacturer_existingId() {
        when(repositoryMock.findManufacturerById(manufacturerListWithOneManufacturer.get(0).getId()))
                .thenReturn(manufacturerListWithOneManufacturer.get(0));

        Assertions.assertDoesNotThrow(() -> {
            service.delete(manufacturerListWithOneManufacturer.get(0).getId());
        }, "Exception was not expected.");

        verify(repositoryMock, times(1)).deleteById(manufacturerListWithOneManufacturer.get(0).getId());
    }

}
