package com.example.kindergarten.services;

import com.example.kindergarten.entities.Nationality;
import com.example.kindergarten.repositories.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NationalityService {
    private final NationalityRepository nationalityRepository;

    @Autowired
    public NationalityService(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    public List<Nationality> findAll() {return nationalityRepository.findAll();}

    public Optional<Nationality> findById(Integer id) {return nationalityRepository.findById(id);}

    public Nationality saveNationality(Nationality nationality) {return nationalityRepository.save(nationality);}

    public void deleteNationalityById(Integer id) {nationalityRepository.deleteById(id);}
}
