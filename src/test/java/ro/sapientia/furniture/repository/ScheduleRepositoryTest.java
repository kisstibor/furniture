package ro.sapientia.furniture.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @AfterEach
    public void tearDown() {
        entityManager.clear();
    }

    public void createOne() {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        manufacturer = entityManager.persistAndFlush(entityManager.merge(manufacturer));

        ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>()
        );

        manufacturerLocation = entityManager.persistAndFlush(entityManager.merge(manufacturerLocation));

        Schedule schedule= new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                manufacturerLocation
        );

        schedule = entityManager.persistAndFlush(entityManager.merge(schedule));
    }

    @Test
    public void testFindAll_empty() {
        final List<Schedule> expectedSchedules = scheduleRepository.findAll();
        Assertions.assertEquals(0, expectedSchedules.size());
    }

    @Test
    public void testFindAll_one() {
        createOne();

        List<Schedule> expectedSchedules = scheduleRepository.findAll();

        Assertions.assertEquals(1, expectedSchedules.size());
    }

    @Test
    public void testFindScheduleById_fail() {
        final Schedule schedule = scheduleRepository.findScheduleById(100L);

        Assertions.assertNull(schedule);
    }

    @Test
    public void testFindById_success() {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        manufacturer = entityManager.persistAndFlush(entityManager.merge(manufacturer));

        ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>()
        );

        manufacturerLocation = entityManager.persistAndFlush(entityManager.merge(manufacturerLocation));

        Schedule expectedSchedule = new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                manufacturerLocation
        );

        expectedSchedule = entityManager.persistAndFlush(entityManager.merge(expectedSchedule));

        final Schedule actualScedule = scheduleRepository.findScheduleById(expectedSchedule.getId());

        Assertions.assertNotNull(actualScedule);
        Assertions.assertEquals(expectedSchedule, actualScedule);
    }

    @Test
    public void testCreate_one() {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        manufacturer = entityManager.persistAndFlush(entityManager.merge(manufacturer));

        ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>()
        );

        manufacturerLocation = entityManager.persistAndFlush(entityManager.merge(manufacturerLocation));

        Schedule schedule= new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                manufacturerLocation
        );

        schedule = scheduleRepository.saveAndFlush(schedule);

        Assertions.assertEquals(schedule, scheduleRepository.findScheduleById(schedule.getId()));
    }

    @Test
    public void testDeleteById_notFound() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> scheduleRepository.deleteById(10L));
    }


}
