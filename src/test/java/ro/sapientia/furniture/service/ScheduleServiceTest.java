package ro.sapientia.furniture.service;

import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.repository.ScheduleRepository;
import ro.sapientia.furniture.util.StatusMessage;

import java.time.Instant;
import java.util.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ScheduleServiceTest {

    private ScheduleRepository scheduleRepositoryMock;
    private ScheduleService scheduleService;

    @BeforeEach
    public void setUp() {
        scheduleRepositoryMock = mock(ScheduleRepository.class);
        scheduleService = new ScheduleService(scheduleRepositoryMock);

    }

    @Test
    public void testFindAllSchedules_emptyList() {
        when(scheduleRepositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<Schedule> schedules = scheduleService.findAllSchedules();

        Assertions.assertEquals(0, schedules.size());
    }

    @Test
    public void testFindAllSchedules_null() {
        when(scheduleRepositoryMock.findAll()).thenReturn(null);
        final List<Schedule> schedules = scheduleService.findAllSchedules();

        Assertions.assertNull(schedules);
    }

    @Test
    public void testFindAllSchedules_allTwo() {
        final Schedule scheduleMock1 = mock(Schedule.class);
        final Schedule scheduleMock2 = mock(Schedule.class);

        final List<Schedule> schedulesWithMockedElements = new ArrayList<>();

        schedulesWithMockedElements.add(scheduleMock1);
        schedulesWithMockedElements.add(scheduleMock2);

        when(scheduleRepositoryMock.findAll()).thenReturn(schedulesWithMockedElements);
        final List<Schedule> schedules = scheduleService.findAllSchedules();

        Assertions.assertEquals(2, schedules.size());
    }

    @Test
    public void testFindScheduleById_invalid() {
        when(scheduleRepositoryMock.findScheduleById(anyLong())).thenReturn(null);
        NotFoundException thrownException = Assertions.assertThrows(NotFoundException.class, () -> scheduleService.findScheduleById(1L));

        Assertions.assertEquals(
                StatusMessage.NOT_FOUND,
                thrownException.getMessage()
        );
    }

    @Test
    public void testFindScheduleById_valid() {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        final Schedule expectedSchedule = new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                mock(ManufacturerLocation.class)
        );

        when(scheduleRepositoryMock.findScheduleById(anyLong())).thenReturn(expectedSchedule);

        Assertions.assertDoesNotThrow( () -> {
            final Schedule actualSchedule = scheduleService.findScheduleById(expectedSchedule.getId());

            Assertions.assertNotNull(expectedSchedule);
            Assertions.assertEquals(expectedSchedule, actualSchedule);
        } );
    }

    @Test
    public void testCreate() {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        final Schedule expectedSchedule = new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                mock(ManufacturerLocation.class)
        );

        when(scheduleRepositoryMock.saveAndFlush(any(Schedule.class))).thenReturn(expectedSchedule);
        Schedule schedule = scheduleService.create(expectedSchedule);

        Assertions.assertNotNull(schedule.getProduct());
        Assertions.assertNotNull(schedule.getStart_date());
        Assertions.assertNotNull(schedule.getEnd_date());
        Assertions.assertNotNull(schedule.getManufacturerLocation());

    }

    @Test
    public void testUpdateSchedule_valid() {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        final Schedule schedule = new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                mock(ManufacturerLocation.class)
        );

        schedule.setProduct("updatedProduct");

        when(scheduleRepositoryMock.saveAndFlush(any(Schedule.class))).thenReturn(schedule);
        final Schedule expectedSchedule = scheduleService.update(schedule);

        Assertions.assertEquals(expectedSchedule.getProduct(), schedule.getProduct());
    }

    @Test
    public void testDeleteSchedule_invalidId() {
        when(scheduleRepositoryMock.findScheduleById(anyLong())).thenReturn(null);

        NotFoundException thrownException = Assertions.assertThrows(NotFoundException.class, () -> scheduleService.delete(anyLong()));

        Assertions.assertEquals(StatusMessage.NOT_FOUND, thrownException.getMessage());

        verify(scheduleRepositoryMock, times(0)).deleteById(anyLong());
    }

    @Test
    public void testDeleteSchedule_validId() {
        Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        final Schedule schedule = new Schedule(
                1L,
                "schedule1",
                start_date,
                end_date,
                mock(ManufacturerLocation.class)
        );

        when(scheduleRepositoryMock.findScheduleById(anyLong())).thenReturn(schedule);

        Assertions.assertDoesNotThrow( () -> scheduleService.delete(schedule.getId()));

        verify(scheduleRepositoryMock, times(1)).deleteById(schedule.getId());
    }

}
