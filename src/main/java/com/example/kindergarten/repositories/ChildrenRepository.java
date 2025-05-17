package com.example.kindergarten.repositories;

import com.example.kindergarten.entities.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChildrenRepository extends JpaRepository<Children, Integer> {
    @Query("SELECT c FROM Children c WHERE c.gruppa.gruppa IN :groupNames")
    List<Children> findByGruppaNames(@Param("groupNames") List<String> groupNames);

}
