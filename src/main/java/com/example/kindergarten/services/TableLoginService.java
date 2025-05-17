package com.example.kindergarten.services;

import com.example.kindergarten.entities.TableLogin;
import com.example.kindergarten.repositories.TableLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableLoginService {

    private final TableLoginRepository tableLoginRepository;

    @Autowired
    public TableLoginService(TableLoginRepository tableLoginRepository) {
        this.tableLoginRepository = tableLoginRepository;
    }

    public List<TableLogin> getAllTableLogins() {return tableLoginRepository.findAll();}

    public Optional<TableLogin> getTableLoginById(Integer id) {return tableLoginRepository.findById(id);}

    public TableLogin saveTableLogin(TableLogin tableLogin) {return tableLoginRepository.save(tableLogin);}

    public void deleteTableLoginById(Integer id) {tableLoginRepository.deleteById(id);}
}
