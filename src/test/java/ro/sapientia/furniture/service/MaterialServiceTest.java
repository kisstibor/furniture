package ro.sapientia.furniture.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.error.MaterialNotFoundException;
import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.model.Material;
import ro.sapientia.furniture.repository.FurnitureBodyRepository;
import ro.sapientia.furniture.repository.MaterialRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MaterialServiceTest {

    private MaterialRepository materialRepositoryMock;

    private MaterialService materialService;

    @BeforeEach
    public void setUp() {
        materialRepositoryMock = mock(MaterialRepository.class);
        materialService = new MaterialService(materialRepositoryMock);
    }

    @Test
    public void findAllMaterials_emptyList() {
        when(materialRepositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<Material> bodies =  materialService.findAllMaterials();

        assertEquals(0, bodies.size());
    }


    @Test
    public void findAllMaterials_null() {
        when(materialRepositoryMock.findAll()).thenReturn(null);
        final List<Material> bodies =  materialService.findAllMaterials();

        assertNull(bodies);
    }


    @Test
    public void testGetMaterialById_withInvalidId() {
        when(materialRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(MaterialNotFoundException.class, () -> materialService.findMaterialById(10L));
    }


}
