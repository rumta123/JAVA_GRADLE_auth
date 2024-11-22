package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Получить все записи
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Получить запись по ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Добавить новую запись
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Обновить запись
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id)
                .map(existingAppointment -> {
                    existingAppointment.setClient(updatedAppointment.getClient());
                    existingAppointment.setMaster(updatedAppointment.getMaster());
                    existingAppointment.setQualification(updatedAppointment.getQualification());
                    existingAppointment.setDateTime(updatedAppointment.getDateTime());
                    existingAppointment.setServices(updatedAppointment.getServices());
                    return appointmentRepository.save(existingAppointment);
                })
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found"));
    }

    // Удалить запись
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Найти записи по клиенту
    public List<Appointment> getAppointmentsByClientId(Long clientId) {
        return appointmentRepository.findByClientId(clientId);
    }

    // Найти записи по мастеру
    public List<Appointment> getAppointmentsByMasterId(Long masterId) {
        return appointmentRepository.findByMasterId(masterId);
    }

    // Найти записи в указанном временном диапазоне
    public List<Appointment> getAppointmentsByDateRange(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDateTimeBetween(start, end);
    }
}
