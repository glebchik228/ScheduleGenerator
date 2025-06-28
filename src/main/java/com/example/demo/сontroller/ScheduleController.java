package com.example.demo.сontroller;

import com.example.demo.model.TimeTable;
import com.example.demo.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<TimeTable> createSchedule(@RequestBody TimeTable input) {
        TimeTable solution = scheduleService.solve(input);
        return ResponseEntity.ok(solution);
    }

    // TODO: request queue with allocation of time for the whole night
    // TODO: authorization authentication
    @GetMapping
    public ResponseEntity<TimeTable> getSchedule() {
        TimeTable current = scheduleService.getCurrentSolution();
        if (current == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(current);
    }
}
