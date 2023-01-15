package ro.sapientia.furniture;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ScheduleEETest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    private void addOneElement() {
        final Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        final Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        final Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        entityManager.persist(entityManager.merge(manufacturer));

        final ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        entityManager.persist(entityManager.merge(manufacturerLocation));

        final Schedule schedule= new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                manufacturerLocation
        );

        entityManager.persist(entityManager.merge(schedule));
        entityManager.flush();
    }

    @Test
    void testFindAllSchedules_emptyList() throws Exception {
        mvc.perform(get("/schedule/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    void testFindAllSchedules_oneElement() throws Exception{

        addOneElement();

        mvc.perform(get("/schedule/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].product", is("schedule1")))
                .andExpect(jsonPath("$[0].start_date", is("2022-05-05T11:50:55.912+00:00")))
                .andExpect(jsonPath("$[0].end_date", is("2022-12-05T11:50:55.912+00:00")));
    }

    @Test
    public void testFindScheduleById_notFound() throws Exception {
        mvc.perform(get("/schedule/find/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindScheduleById_succeed() throws Exception {
        addOneElement();
        mvc.perform(get("/schedule/find/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.product", is("schedule1")));
    }

    @Test
    public void testCreateSchedule_error() throws Exception {
        mvc.perform(post("/schedule/create")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdateSchedule_success() throws Exception {
        addOneElement();

        final ObjectMapper objectMapper = new ObjectMapper();

        final Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        final Date end_date = Date.from(Instant.parse("2023-01-05T11:50:55.912Z"));

        final Manufacturer manufacturer = new Manufacturer(
                2L,
                "manufacturer1",
                null
        );

        final ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                2L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        final Schedule schedule= new Schedule(
                1L,
                "updatedProduct",
                start_date,
                end_date,
                manufacturerLocation
        );

        mvc.perform(MockMvcRequestBuilders.put("/schedule/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product", is(schedule.getProduct())));
    }

    @Test
    public void testUpdateSchedule_error() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();

        final Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        final Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        final Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        final ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        final Schedule schedule= new Schedule(
                100L,
                "product1",
                start_date,
                end_date,
                manufacturerLocation
        );

        mvc.perform(MockMvcRequestBuilders.put("/schedule/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteSchedule_notFound() throws Exception {
        mvc.perform(delete("/schedule/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteScheduleById_succeed() throws Exception {
        addOneElement();
        mvc.perform(delete("/schedule/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
