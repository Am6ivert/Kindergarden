package com.example.kindergarten.repositories;

import com.example.kindergarten.entities.TableLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableLoginRepository extends JpaRepository<TableLogin, Integer> {
    TableLogin findByUserLogin(String userLogin);
}
