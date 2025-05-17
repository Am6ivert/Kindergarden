package com.example.kindergarten.repositories;

import com.example.kindergarten.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
