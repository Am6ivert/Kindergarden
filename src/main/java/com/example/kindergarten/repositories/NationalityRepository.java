package com.example.kindergarten.repositories;

import com.example.kindergarten.entities.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
}
