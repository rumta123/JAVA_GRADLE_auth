package com.example.demo.controller;

import com.example.demo.model.Month;
import com.example.demo.service.MonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/months")
public class MonthController {

    private final MonthService monthService;

    @Autowired
    public MonthController(MonthService monthService) {
        this.monthService = monthService;
    }

    // Получить все месяцы
    @GetMapping
    public List<Month> getAllMonths() {
        return monthService.getAllMonths();
    }

    // Получить месяц по ID
    @GetMapping("/{id}")
    public ResponseEntity<Month> getMonthById(@PathVariable Long id) {
        Optional<Month> month = monthService.getMonthById(id);
        return month.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавить новый месяц
    @PostMapping
    public ResponseEntity<Month> addMonth(@RequestBody Month month) {
        Month savedMonth = monthService.addMonth(month);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMonth);
    }

    // Обновить месяц
    @PutMapping("/{id}")
    public ResponseEntity<Month> updateMonth(@PathVariable Long id, @RequestBody Month updatedMonth) {
        try {
            Month month = monthService.updateMonth(id, updatedMonth);
            return ResponseEntity.ok(month);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить месяц
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonth(@PathVariable Long id) {
        monthService.deleteMonth(id);
        return ResponseEntity.noContent().build();
    }
}
