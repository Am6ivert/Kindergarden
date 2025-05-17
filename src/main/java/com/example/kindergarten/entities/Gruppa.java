package com.example.kindergarten.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "gruppa")
public class Gruppa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "gruppa")
    private String gruppa;

    @OneToMany(mappedBy = "gruppa")
    private List<Children> childrenList;

    @OneToMany(mappedBy = "gruppa")
    private List<Employee> employeeList;
}
