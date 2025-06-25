package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee", schema = "public")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "image", length = 512)
    private String image;

    private String telephon;

    private String mail;


    @Builder.Default
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Education> education = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> project;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "id_employee"))
    private List<Skill> skill;

    @Column(name = "english_level")
    @Enumerated(EnumType.ORDINAL)
    private EnglishLevel englishlevel;

    // Конструктор для создания сотрудника с полной информацией
    public Employee(int id, String name, String image, String telephon, String mail,
                    EnglishLevel englishlevel, Set<Education> education,
                    Set<Project> project, List<Skill> skill) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.telephon = telephon;
        this.mail = mail;
        this.englishlevel = englishlevel;
        this.education = education;
        this.project = project;
        this.skill = skill;
    }

    // Геттеры и сеттеры (если не используется Lombok)

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTelephon(String telephon) {
        this.telephon = telephon;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setEducation(Set<Education> education) {
        this.education = education;
    }

    public void setProject(Set<Project> project) {
        this.project = project;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }
}