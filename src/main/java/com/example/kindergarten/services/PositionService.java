package com.example.kindergarten.services;

import com.example.kindergarten.entities.Position;
import com.example.kindergarten.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAllPositions() {return positionRepository.findAll();}

    public Optional<Position> getPositionById(Integer id) {return positionRepository.findById(id);}

    public Position savePosition(Position position) {return positionRepository.save(position);}

    public void deletePositionById(Integer id) {positionRepository.deleteById(id);}
}
