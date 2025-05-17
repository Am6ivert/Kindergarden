package com.example.kindergarten.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "FIO")
    private String fio;

    @Column(name = "dateborn")
    private LocalDate dateborn;

    @ManyToOne
    @JoinColumn(name = "position", referencedColumnName = "id")
    private Position position;

    @Column(name = "adres")
    private String adres;

    @Column(name = "tel_num")
    private String telNum;

    @ManyToOne
    @JoinColumn(name = "kruzhok", referencedColumnName = "id")
    private Kruzhok kruzhok;

    @ManyToOne
    @JoinColumn(name = "gruppa", referencedColumnName = "id")
    private Gruppa gruppa;
}
