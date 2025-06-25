package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "education", schema = "public")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "yearstart")
    private int yearStart;

    @Column(name = "yearend")
    private int yearEnd;

    @Column(name = "nameeducation")
    private String nameEducation;

    private String degree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_education", nullable = false) // nullable = false — важно!
    private Employee employee;

    // Конструктор без id (для удобства создания новых записей)
    public Education(int yearStart, int yearEnd, String nameEducation, String degree) {
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
        this.nameEducation = nameEducation;
        this.degree = degree;
    }

    // Геттеры и сеттеры (если не используется Lombok)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYearStart() {
        return yearStart;
    }

    public void setYearStart(int yearStart) {
        this.yearStart = yearStart;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(int yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getNameEducation() {
        return nameEducation;
    }

    public void setNameEducation(String nameEducation) {
        this.nameEducation = nameEducation;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}