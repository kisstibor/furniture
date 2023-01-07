package ro.sapientia.furniture.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.repository.ScheduleRepository;
import ro.sapientia.furniture.util.StatusMessage;

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

    public Schedule findScheduleById(final Long id) throws NotFoundException {
        Schedule schedule = this.scheduleRepository.findScheduleById(id);
        if (schedule != null) {
            return schedule;
        } else {
            throw new NotFoundException(StatusMessage.NOT_FOUND);
        }
    }

    public Schedule create(Schedule schedule) {
        return this.scheduleRepository.saveAndFlush(schedule);
    }

    public Schedule update(Schedule schedule) {
        return this.scheduleRepository.saveAndFlush(schedule);
    }

    public void delete(Long id) throws NotFoundException {
        if (scheduleRepository.findScheduleById(id) != null) {
            this.scheduleRepository.deleteById(id);
        } else {
            throw new NotFoundException(StatusMessage.NOT_FOUND);
        }

    }
}
