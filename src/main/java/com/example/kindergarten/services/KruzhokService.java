package com.example.kindergarten.services;

import com.example.kindergarten.entities.Kruzhok;
import com.example.kindergarten.repositories.KruzhokRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KruzhokService {

    private final KruzhokRepository kruzhokRepository;

    @Autowired
    public KruzhokService(KruzhokRepository kruzhokRepository) {
        this.kruzhokRepository = kruzhokRepository;
    }

    public List<Kruzhok> getAllKruzhoks() {return kruzhokRepository.findAll();}

    public Optional<Kruzhok> getKruzhokById(Integer id) {return kruzhokRepository.findById(id);}

    public Kruzhok saveKruzhok(Kruzhok kruzhok) {return kruzhokRepository.save(kruzhok);}

    public void deleteKruzhokById(Integer id) {kruzhokRepository.deleteById(id);}
}
