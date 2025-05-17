package com.example.kindergarten.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "children")
public class Children {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "FIO")
    private String fio;

    @Column(name = "dateborn")
    private LocalDate dateborn;

    @Column(name = "adres")
    private String adres;

    @Column(name = "tel_pap")
    private String telPap;

    @Column(name = "tel_mam")
    private String telMam;

    @ManyToOne
    @JoinColumn(name = "gruppa", referencedColumnName = "id")
    private Gruppa gruppa;

    @ManyToOne
    @JoinColumn(name = "kruzhok", referencedColumnName = "id")
    private Kruzhok kruzhok;

    @ManyToOne
    @JoinColumn(name = "nationality", referencedColumnName = "id")
    private Nationality nationality;
}
