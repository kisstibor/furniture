package ro.sapientia.furniture;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.model.Stock;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
class ManufacturerLocationEETest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Manufacturer> manufacturers = new ArrayList<>();
    private List<ManufacturerLocation> locations = new ArrayList<>();
    private Set<Stock> stocks = new HashSet<>();
    private Set<Schedule> schedules = new HashSet<>();

    @BeforeEach
    public void setUp() {
        manufacturers.add( new Manufacturer(
                1L,
                "manufacturer1"
        ));
        var savedManufacturer = entityManager.persist(entityManager.merge(manufacturers.get(0)));
        locations.add( new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                savedManufacturer,
                schedules,
                stocks
        ));
//        locations.add( new ManufacturerLocation(
//                2L,
//                "manufacturerLocation2",
//                "address2",
//                savedManufacturer,
//                schedules,
//                stocks
//        ));
        entityManager.persist(entityManager.merge(locations.get(0)));
//        schedules.add( new Schedule(
//                1L,
//                "product1",
//                new Date(),
//                new Date(),
//                locations.get(0)
//        ));
//        stocks.add( new Stock(
//                1L,
//                "product1",
//                1,
//                locations.get(0)
//        ));
//        entityManager.persist(entityManager.merge(new ArrayList<>(schedules).get(0)));
//        entityManager.persist(entityManager.merge(new ArrayList<>(stocks).get(0)));
//        locations.get(0).setSchedules(schedules);
//        locations.get(0).setStocks(stocks);
//        entityManager.persist(entityManager.merge(locations.get(0)));
        entityManager.flush();
    }

    @Test
    void testFindAllMLSucceed() throws Exception {
        mvc.perform(get("/manufacturer-location/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].schedules.size()", is(0)))
                .andExpect(jsonPath("$[0].stocks.size()", is(0)))
                .andExpect(jsonPath("$[0].name", is("manufacturerLocation1")));
    }

    @Test
    public void testFindMLByIdSucceed() throws Exception {
        mvc.perform(get("/manufacturer-location/find/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(locations.get(0).getName())))
                .andExpect(jsonPath("$.address", is(locations.get(0).getAddress())));
    }

    @Test
    public void testCreateMLSucceed() throws Exception {
        mvc.perform(post("/manufacturer-location/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(locations.get(0))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(locations.get(0).getName())))
                .andExpect(jsonPath("$.address", is(locations.get(0).getAddress())));
    }

    @Test
    public void testDeleteMLByIdSucceed() throws Exception {
        mvc.perform(delete("/manufacturer-location/delete/{id}", 4L))
                .andExpect(status().isOk());
    }

}
