package com.example.kindergarten.services;

import com.example.kindergarten.entities.Employee;
import com.example.kindergarten.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DataSource dataSource;
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DataSource dataSource) {
        this.employeeRepository = employeeRepository;
        this.dataSource = dataSource;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public void updateEmployee(int id, String fio, LocalDate date, Integer position, String adres,
                               String telNum, Integer kruzhok, Integer gruppa) {
        String query = "UPDATE employee SET fio = ?, adres = ?, tel_num = ?, gruppa = ?, kruzhok = ?, position = ?, dateborn = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, fio);
            statement.setDate(2, Date.valueOf(date));
            statement.setInt(3, position);
            statement.setString(4, adres);
            statement.setString(5, telNum);
            statement.setInt(6, kruzhok);
            statement.setInt(7, gruppa);
            statement.setInt(8, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Не удалось обновить сотрудника", e);
            throw new RuntimeException("Ошибка при обновлении сотрудника", e);
        }
    }

    public void insertEmployee(Employee employee) {
        String query = "INSERT INTO employee (fio, dateborn, position, adres, tel_num, kruzhok, gruppa) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, employee.getFio());
            statement.setDate(2, Date.valueOf(employee.getDateborn()));
            statement.setInt(3, employee.getPosition().getId());
            statement.setString(4, employee.getAdres());
            statement.setString(5, employee.getTelNum());
            statement.setInt(6, employee.getKruzhok().getId());
            statement.setInt(7, employee.getGruppa().getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Не удалось вставить сотрудника: {}", e.getMessage(), e);
            throw new RuntimeException("Ошибка при вставке сотрудника: " + e.getMessage(), e);
        }
    }

    public void deleteEmployee(Integer id) {
        String query = "DELETE FROM employee WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Не удалось удалить сотрудника", e);
            throw new RuntimeException("Ошибка при удалении сотрудника", e);
        }
    }
}
