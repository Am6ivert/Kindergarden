package com.example.kindergarten.services;

import com.example.kindergarten.entities.Gruppa;
import com.example.kindergarten.repositories.GruppaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GruppaService {

    private final GruppaRepository gruppaRepository;

    @Autowired
    public GruppaService(GruppaRepository gruppaRepository) {
        this.gruppaRepository = gruppaRepository;
    }

    public List<Gruppa> getAllGruppas() {return gruppaRepository.findAll();}

    public Optional<Gruppa> getGruppaById(Integer id) {return gruppaRepository.findById(id);}

    public Gruppa saveGruppa(Gruppa gruppa) {return gruppaRepository.save(gruppa);}

    public void deleteGruppa(Integer id) {gruppaRepository.deleteById(id);}
}
