package com.example.kindergarten.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "kruzhok")
public class Kruzhok {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "kruzhok")
    private String kruzhok;

    @OneToMany(mappedBy = "kruzhok")
    private List<Children> childrenList;

    @OneToMany(mappedBy = "kruzhok")
    private List<Employee> employeeList;
}
