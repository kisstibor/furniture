package ro.sapientia.furniture;

import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import ro.sapientia.furniture.model.Manufacturer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
class ManufacturerEETests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Manufacturer> manufacturerListWithOneManufacturer = new ArrayList<Manufacturer>(Arrays.asList(
            new Manufacturer(1L, "Calligaris", null)
    ));

    private List<Manufacturer> manufacturerListWithMultipleManufacturers = new ArrayList<Manufacturer>(Arrays.asList(
            new Manufacturer(1L, "Actona", null),
            new Manufacturer(3L, "De Sede", null),
            new Manufacturer(9L, "Demeyere", null),
            new Manufacturer(11L, "Gautier", null),
            new Manufacturer(13L, "Telnita", null),
            new Manufacturer(19L, "Rexwood", null)
    ));

    @BeforeEach
    public void setUp() {
        entityManager.clear();
        entityManager.flush();
    }

    @Test
    void testFindAllManufacturersWithEmptyListShouldSucceed() throws Exception {
        mvc.perform(get("/manufacturer/find/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    void testFindAllManufacturersWithOneManufacturerShouldSucceed() throws Exception {
        entityManager.persist(entityManager.merge(manufacturerListWithOneManufacturer.get(0)));
        mvc.perform(get("/manufacturer/find/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(manufacturerListWithOneManufacturer.get(0).getId().intValue())))
                .andExpect(jsonPath("$.size()", is(manufacturerListWithOneManufacturer.size())));
    }

    @Test
    void testFindAllManufacturersWithMultipleManufacturersShouldSucceed() throws Exception {
        manufacturerListWithMultipleManufacturers.forEach(manufacturer -> {
            entityManager.persist(entityManager.merge(manufacturer));
        });
        mvc.perform(get("/manufacturer/find/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(manufacturerListWithMultipleManufacturers.size())));
    }

    @Test
    void testFindManufacturerShouldSucceed() throws Exception {
        entityManager.persist(entityManager.merge(manufacturerListWithOneManufacturer.get(0)));
        mvc.perform(get("/manufacturer/find/" + manufacturerListWithOneManufacturer.get(0).getId().intValue())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(manufacturerListWithOneManufacturer.get(0).getId().intValue())));
    }

    @Test
    void testFindManufacturerShouldRespondWithNotFound() throws Exception {
        mvc.perform(get("/manufacturer/find/" + manufacturerListWithOneManufacturer.get(0).getId().intValue())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateManufacturerShouldSucceed() throws Exception {
        mvc.perform(post("/manufacturer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(manufacturerListWithOneManufacturer.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(manufacturerListWithOneManufacturer.get(0).getName())));
    }

    @Test
    void testCreateManufacturerShouldFail() throws Exception {
        mvc.perform(post("/manufacturer/create")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testDeleteManufacturerShouldSucceed() throws Exception {
        entityManager.persist(entityManager.merge(manufacturerListWithOneManufacturer.get(0)));
        mvc.perform(delete("/manufacturer/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteManufacturerShouldRespondWithNotFound() throws Exception {
        mvc.perform(delete("/manufacturer/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
