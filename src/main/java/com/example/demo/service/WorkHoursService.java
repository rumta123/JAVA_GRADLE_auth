package com.example.demo.service;

import com.example.demo.model.WorkHours;
import com.example.demo.repository.WorkHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WorkHoursService {

    private final WorkHoursRepository workHoursRepository;

    @Autowired
    public WorkHoursService(WorkHoursRepository workHoursRepository) {
        this.workHoursRepository = workHoursRepository;
    }

    // Получить все рабочие часы
    public List<WorkHours> getAllWorkHours() {
        return workHoursRepository.findAll();
    }

    // Получить рабочие часы по ID
    public Optional<WorkHours> getWorkHoursById(Long id) {
        return workHoursRepository.findById(id);
    }

    // Добавить рабочие часы
    public WorkHours addWorkHours(WorkHours workHours) {
        return workHoursRepository.save(workHours);
    }

    // Обновить рабочие часы
    public WorkHours updateWorkHours(Long id, WorkHours updatedWorkHours) {
        return workHoursRepository.findById(id)
                .map(existingWorkHours -> {
                    existingWorkHours.setWorkDate(updatedWorkHours.getWorkDate());
                    existingWorkHours.setStartTime(updatedWorkHours.getStartTime());
                    existingWorkHours.setEndTime(updatedWorkHours.getEndTime());
                    existingWorkHours.setMaster(updatedWorkHours.getMaster());
                    existingWorkHours.setMonth(updatedWorkHours.getMonth());
                    return workHoursRepository.save(existingWorkHours);
                })
                .orElseThrow(() -> new IllegalArgumentException("WorkHours with ID " + id + " not found"));
    }

    // Удалить рабочие часы
    public void deleteWorkHours(Long id) {
        workHoursRepository.deleteById(id);
    }

    // Получить рабочие часы по дате
    public List<WorkHours> getWorkHoursByDate(LocalDate workDate) {
        return workHoursRepository.findByWorkDate(workDate);
    }
}
