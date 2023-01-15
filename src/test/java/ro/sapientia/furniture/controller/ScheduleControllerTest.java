package ro.sapientia.furniture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.service.ScheduleService;

import javax.ws.rs.InternalServerErrorException;
import java.time.Instant;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = ScheduleController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ScheduleControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean(ScheduleService.class)
    private ScheduleService scheduleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        final Schedule schedule = new Schedule();
        schedule.setProduct("product");
        when(scheduleService.findAllSchedules()).thenReturn(List.of(schedule));

        this.mockMvc.perform(get("/schedule/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].product", is("product")));
    }

    @Test
    public void testFindAllSchedulesShouldFail() throws Exception {
        when(scheduleService.findAllSchedules()).thenThrow(new InternalServerErrorException());

        this.mockMvc.perform(get("/schedule/all")).andExpect(status().is5xxServerError());
    }

    @Test
    public void testFindAllSchedulesShouldSucceedWithEmptyList() throws Exception {
        when(scheduleService.findAllSchedules()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/schedule/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    public void testFindAllSchedulesShouldSucceedWithOneSchedule() throws Exception {

        final List<Schedule> schedules = new ArrayList<>();

        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        Schedule sc1= new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                manufacturerLocation
        );

        schedules.add(sc1);

        when(scheduleService.findAllSchedules()).thenReturn(schedules);

        this.mockMvc.perform(get("/schedule/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(schedules.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].product", is(schedules.get(0).getProduct())))
                .andExpect(jsonPath("$[0].start_date", is("2022-05-05T11:50:55.912+00:00")))
                .andExpect(jsonPath("$[0].end_date", is("2022-12-05T11:50:55.912+00:00")));

    }

    @Test
    public void testFindAllSchedulesShouldSucceedWithThreeSchedule() throws Exception {

        final List<Schedule> schedules = new ArrayList<>();

        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        Schedule schedule1 = new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                manufacturerLocation
        );

        Schedule schedule2 = new Schedule(
                1L,
                "schedule2",
                start_date,
                end_date,
                manufacturerLocation
        );

        Schedule schedule3 = new Schedule(
                1L,
                "schedule2",
                start_date,
                end_date,
                manufacturerLocation
        );

        schedules.add(schedule1);
        schedules.add(schedule2);
        schedules.add(schedule3);

        when(scheduleService.findAllSchedules()).thenReturn(schedules);

        this.mockMvc.perform(get("/schedule/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(schedules.get(0).getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(schedules.get(1).getId().intValue())))
                .andExpect(jsonPath("$[2].id", is(schedules.get(2).getId().intValue())));

    }

    @Test
    public void testFindScheduleByIdShouldSuccess() throws Exception {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        Schedule schedule = new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                manufacturerLocation
        );

        when(scheduleService.findScheduleById(anyLong())).thenReturn(schedule);

        this.mockMvc.perform(get("/schedule/find/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product", is(schedule.getProduct())))
        ;
    }

    @Test
    public void testCreateScheduleShouldSucceed() throws Exception {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        Schedule sc1= new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                manufacturerLocation
        );

        when(scheduleService.create(any(Schedule.class))).thenReturn(sc1);

        mockMvc.perform(post("/schedule/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sc1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.product", is(sc1.getProduct())));

    }

    @Test
    public void testUpdateScheduleShouldSucceed() throws Exception {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        Schedule schedule = new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                manufacturerLocation
        );

        when(scheduleService.update(any(Schedule.class))).thenReturn(schedule);

        mockMvc.perform(put("/schedule/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(schedule)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product", is(schedule.getProduct())));
    }

    @Test
    public void testDeleteScheduleShouldSucceed() throws Exception {
        doNothing().when(scheduleService).delete(anyLong());

        mockMvc.perform(delete("/schedule/delete/{id}", 1L))
                .andExpect(status().isOk());
    }

}
