package ro.sapientia.furniture.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class MaterialRepositoryTest  {

    @Autowired
    MaterialRepository materialRepository;

    @Test
    public void myTest() {
        var result = materialRepository.findAll();
        assertEquals(0, result.size());
    }


    @Test
    public void testDeleteByIdWithUnexistingId() {
        assertThrows(EmptyResultDataAccessException.class, () -> materialRepository.deleteById(10L));
    }

}
