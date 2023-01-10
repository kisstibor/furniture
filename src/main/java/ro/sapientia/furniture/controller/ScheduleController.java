package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.service.ScheduleService;
import ro.sapientia.furniture.util.ErrorHandling;
import ro.sapientia.furniture.util.StatusMessage;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSchedules() {
        try {
            return new ResponseEntity<>(scheduleService.findAllSchedules(), HttpStatus.OK);
        }
        catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSchedule(@RequestBody Schedule schedule) {
        try {
            return new ResponseEntity<>(scheduleService.create(schedule), HttpStatus.CREATED);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSchedule(@RequestBody Schedule schedule) {
        try {
            return new ResponseEntity<>(scheduleService.update(schedule), HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long id) {
        try {
            scheduleService.delete(id);
            return new ResponseEntity<>(StatusMessage.OK, HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }

    }
}
