package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.repository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findAllSchedules() {
        return this.scheduleRepository.findAll();
    }

    public Schedule findScheduleById(final Long id) {
        return this.scheduleRepository.findScheduleById(id);
    }

    public Schedule create(Schedule schedule) {
        return this.scheduleRepository.saveAndFlush(schedule);
    }

    public Schedule update(Schedule schedule) {
        return this.scheduleRepository.saveAndFlush(schedule);
    }

    public void delete(Long id) {
        this.scheduleRepository.deleteById(id);
    }
}
