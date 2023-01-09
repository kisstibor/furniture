package ro.sapientia.furniture.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import ro.sapientia.furniture.model.FurniturePanel;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.*;
import static ro.sapientia.furniture.util.FurniturePanelDatabaseBuilder.buildTestFurniturePanels;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class FurniturePanelRepositoryTest {

    @Autowired
    private FurniturePanelRepository repository;


    @BeforeEach
    public void setUp() {
        repository.saveAllAndFlush(buildTestFurniturePanels());
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void itShouldCheckIfFurniturePanelFindAllExecutes() {
        var result = repository.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void itShouldCheckIfFurniturePanelByIdDoesNotExist() {
        //given
        long id = 1000;

        //when
        FurniturePanel furniturePanel = repository.findFurniturePanelById(id);

        //then
        assertNull(furniturePanel);
    }

    @Test
    public void itShouldCheckIfFurniturePanelByIdExists() {
        //given
        long existingId = repository.findAll().get(0).getId();

        //when
        FurniturePanel furniturePanel = repository.findFurniturePanelById(existingId);

        //then
        assertNotNull(furniturePanel);
    }

    @Test
    public void itShouldCheckIfFurniturePanelDeleteByIdDoesNotExecute() {
        assertThrows(EmptyResultDataAccessException.class, () -> repository.deleteById(100L));
    }

    @Test
    public void itShouldCheckIfFurniturePanelDeleteByIdExecutes() {
        //when
        long existingId = repository.findAll().get(0).getId();
        final var furniturePanels = repository.findAll();
        repository.deleteById(existingId);
        final var furniturePanelsAfterDelete = repository.findAll();
        final var deletedFurniturePanels = repository.findById(existingId);

        //then
        assertEquals(1, furniturePanels.size() - furniturePanelsAfterDelete.size());
        assertTrue(deletedFurniturePanels.isEmpty());
    }
}

