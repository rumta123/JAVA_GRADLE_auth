package com.example.demo.service;

import com.example.demo.model.Month;
import com.example.demo.repository.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonthService {

    private final MonthRepository monthRepository;

    @Autowired
    public MonthService(MonthRepository monthRepository) {
        this.monthRepository = monthRepository;
    }

    // Получить все месяцы
    public List<Month> getAllMonths() {
        return monthRepository.findAll();
    }

    // Получить месяц по ID
    public Optional<Month> getMonthById(Long id) {
        return monthRepository.findById(id);
    }

    // Добавить новый месяц
    public Month addMonth(Month month) {
        return monthRepository.save(month);
    }

    // Обновить данные месяца
    public Month updateMonth(Long id, Month updatedMonth) {
        return monthRepository.findById(id)
                .map(existingMonth -> {
                    existingMonth.setName(updatedMonth.getName());
                    existingMonth.setWorkHours(updatedMonth.getWorkHours());
                    return monthRepository.save(existingMonth);
                })
                .orElseThrow(() -> new IllegalArgumentException("Month with ID " + id + " not found"));
    }

    // Удалить месяц
    public void deleteMonth(Long id) {
        monthRepository.deleteById(id);
    }
}
