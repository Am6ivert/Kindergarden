package com.example.kindergarten.services;

import com.example.kindergarten.entities.Children;
import com.example.kindergarten.repositories.ChildrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.sql.PreparedStatement;


@Service
public class ChildrenService {
    private final Logger logger = LoggerFactory.getLogger(ChildrenService.class);
    private final DataSource dataSource;
    private final ChildrenRepository childrenRepository;

    @Autowired
    public ChildrenService(DataSource dataSource, ChildrenRepository childrenRepository) {
        this.dataSource = dataSource;
        this.childrenRepository = childrenRepository;}

    public List<Children> getAll() {return childrenRepository.findAll();}

    public Optional<Children> getChildById(Integer id) {
        return childrenRepository.findById(id);
    }

    public void updateChild(int id, String fio, LocalDate dateborn, String address,
                            String telDad, String telMom, Integer group, Integer kruzhok, Integer nationality) {
        String query = "UPDATE children SET fio = ?, dateborn = ?, adres = ?, tel_pap = ?, tel_mam = ?, gruppa = ?, kruzhok = ?, nationality = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, fio);
            statement.setDate(2, Date.valueOf(dateborn));
            statement.setString(3, address);
            statement.setString(4, telDad);
            statement.setString(5, telMom);
            statement.setInt(6, group);
            statement.setInt(7, kruzhok);
            statement.setInt(8, nationality);
            statement.setInt(9, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Ошибка при обновлении записи!", e);
            throw new RuntimeException("Ошибка при обновлении ребёнка", e);
        }
    }

    public Optional<Children> getById(Integer id) {
        return childrenRepository.findById(id);
    }

    public void insertChildren(Children child) {
        String query = "INSERT INTO children (fio, dateborn, adres, tel_pap, tel_mam, gruppa, kruzhok, nationality) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, child.getFio());
            statement.setDate(2, Date.valueOf(child.getDateborn()));
            statement.setString(3, child.getAdres());
            statement.setString(4, child.getTelPap());
            statement.setString(5, child.getTelMam());
            statement.setInt(6, child.getGruppa().getId());
            statement.setInt(7, child.getKruzhok().getId());
            statement.setInt(8, child.getNationality().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка при вставке новой записи!", e);
            throw new RuntimeException("Ошибка при вставке ребёнка", e);
        }
    }


    public void deleteChildren(Integer id) {
        String query = "DELETE FROM children WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Не удалось удалить запись!", e);
            throw new RuntimeException("Ошибка при удалении ребёнка", e);
        }
    }

}
