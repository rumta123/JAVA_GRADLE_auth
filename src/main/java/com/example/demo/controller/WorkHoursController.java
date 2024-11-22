package com.example.demo.controller;

import com.example.demo.model.WorkHours;
import com.example.demo.service.WorkHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/work-hours")
public class WorkHoursController {

    private final WorkHoursService workHoursService;

    @Autowired
    public WorkHoursController(WorkHoursService workHoursService) {
        this.workHoursService = workHoursService;
    }

    // Получить все рабочие часы
    @GetMapping
    public List<WorkHours> getAllWorkHours() {
        return workHoursService.getAllWorkHours();
    }

    // Получить рабочие часы по ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkHours> getWorkHoursById(@PathVariable Long id) {
        Optional<WorkHours> workHours = workHoursService.getWorkHoursById(id);
        return workHours.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавить новые рабочие часы
    @PostMapping
    public ResponseEntity<WorkHours> addWorkHours(@RequestBody WorkHours workHours) {
        WorkHours savedWorkHours = workHoursService.addWorkHours(workHours);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWorkHours);
    }

    // Обновить рабочие часы
    @PutMapping("/{id}")
    public ResponseEntity<WorkHours> updateWorkHours(@PathVariable Long id, @RequestBody WorkHours updatedWorkHours) {
        try {
            WorkHours workHours = workHoursService.updateWorkHours(id, updatedWorkHours);
            return ResponseEntity.ok(workHours);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить рабочие часы
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkHours(@PathVariable Long id) {
        workHoursService.deleteWorkHours(id);
        return ResponseEntity.noContent().build();
    }

    // Получить рабочие часы по дате
    @GetMapping("/by-date")
    public List<WorkHours> getWorkHoursByDate(@RequestParam("date") LocalDate workDate) {
        return workHoursService.getWorkHoursByDate(workDate);
    }
}
