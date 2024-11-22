package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;

    // Связь один-ко-многим с WorkHours
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkHours> workHours;

    // Связь многие ко многим с Qualification
    @ManyToMany
    @JoinTable(
            name = "master_qualification", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "master_id"), // Колонка для связи с Master
            inverseJoinColumns = @JoinColumn(name = "qualification_id") // Колонка для связи с Qualification
    )
    private List<Qualification> qualifications;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<WorkHours> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(List<WorkHours> workHours) {
        this.workHours = workHours;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
}
