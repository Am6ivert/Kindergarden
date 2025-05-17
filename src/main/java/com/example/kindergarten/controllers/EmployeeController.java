package com.example.kindergarten.controllers;

import com.example.kindergarten.entities.Employee;
import com.example.kindergarten.services.EmployeeService;
import com.example.kindergarten.services.GruppaService;
import com.example.kindergarten.services.KruzhokService;
import com.example.kindergarten.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PositionService positionService;
    private final KruzhokService kruzhokService;
    private final GruppaService gruppaService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PositionService positionService, KruzhokService kruzhokService, GruppaService gruppaService) {
        this.employeeService = employeeService;
        this.positionService = positionService;
        this.kruzhokService = kruzhokService;
        this.gruppaService = gruppaService;
    }

    @GetMapping
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee/list";
    }

    @PostMapping
    public String saveOrUpdateEmployee(@ModelAttribute Employee employee) {
        if (employee.getId() != null) {
            employeeService.updateEmployee(
                    employee.getId(), employee.getFio(), employee.getDateborn(),
                    employee.getPosition().getId(), employee.getAdres(),
                    employee.getTelNum(), employee.getKruzhok().getId(),
                    employee.getGruppa().getId()
            );
        } else {
            employeeService.insertEmployee(employee);
        }
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        model.addAttribute("employees", employee);
        model.addAttribute("positions", positionService.getAllPositions());
        model.addAttribute("kruzhki", kruzhokService.getAllKruzhoks());
        model.addAttribute("gruppas", gruppaService.getAllGruppas());

        return "employee/form";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employees", employee);
        model.addAttribute("positions", positionService.getAllPositions());
        model.addAttribute("kruzhki", kruzhokService.getAllKruzhoks());
        model.addAttribute("gruppas", gruppaService.getAllGruppas());
        return "employee/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee";
    }
}
