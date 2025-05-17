package com.example.kindergarten.controllers;

import com.example.kindergarten.entities.Children;
import com.example.kindergarten.repositories.ChildrenRepository;
import com.example.kindergarten.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/children")
public class ChildrenController {
    private final ChildrenService childrenService;
    private final GruppaService gruppaService;
    private final KruzhokService kruzhokService;
    private final NationalityService nationalityService;
    private final ChildrenRepository childrenRepository;
    private final ReportService reportService;

    @Autowired
    public ChildrenController(ChildrenService childrenService, GruppaService gruppaService, KruzhokService kruzhokService, NationalityService nationalityService, ChildrenRepository childrenRepository, ReportService reportService) {
        this.childrenService = childrenService;
        this.gruppaService = gruppaService;
        this.kruzhokService = kruzhokService;
        this.nationalityService = nationalityService;
        this.childrenRepository = childrenRepository;
        this.reportService = reportService;
    }

    @GetMapping
    public String listChildren(Model model) {
        model.addAttribute("children", childrenService.getAll());
        return "children/list";
    }

    @GetMapping("/edit/{id}")
    public String editChild(@PathVariable int id, Model model) {
        Children child = childrenService.getChildById(id).orElseThrow(() -> new IllegalArgumentException("Неверный ID: " + id));

        model.addAttribute("children", child);
        model.addAttribute("gruppas", gruppaService.getAllGruppas());
        model.addAttribute("kruzhki", kruzhokService.getAllKruzhoks());
        model.addAttribute("nationalities", nationalityService.findAll());
        return "children/form";
    }

    @PostMapping
    public String saveOrUpdateChild(@ModelAttribute Children child) {
        if (child.getId() != null) {
            childrenService.updateChild(
                    child.getId(), child.getFio(), child.getDateborn(), child.getAdres(),
                    child.getTelPap(), child.getTelMam(),
                    child.getGruppa().getId(), child.getKruzhok().getId(), child.getNationality().getId()
            );
        } else {
            childrenService.insertChildren(child);
        }
        return "redirect:/children";
    }

    @GetMapping("/new")
    public String newChildren(Model model) {
        model.addAttribute("children", new Children());
        model.addAttribute("gruppas", gruppaService.getAllGruppas());
        model.addAttribute("kruzhki", kruzhokService.getAllKruzhoks());
        model.addAttribute("nationalities", nationalityService.findAll());
        return "children/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteChildren(@PathVariable Integer id) {
        childrenService.deleteChildren(id);
        return "redirect:/children";
    }

    @GetMapping("/yasli")
    public String showYasliGroup(Model model) {
        List<String> groupNames = List.of("Y1", "Y2", "Y3", "Y4"); // названия ясельных групп
        model.addAttribute("children", childrenRepository.findByGruppaNames(groupNames));
        return "yasli/list";
    }

    @GetMapping("/mladwyje")
    public String showMladshayaGroup(Model model) {
        List<String> groupNames = List.of("C1", "C2");
        model.addAttribute("children", childrenRepository.findByGruppaNames(groupNames));
        return "mladwyje/list";
    }

    @GetMapping("/srednije")
    public String showSrednyayaGroup(Model model) {
        List<String> groupNames = List.of("B1", "B2", "B3");
        model.addAttribute("children", childrenRepository.findByGruppaNames(groupNames));
        return "srednije/list";
    }

    @GetMapping("/starwije")
    public String showStarshayaGroup(Model model) {
        List<String> groupNames = List.of("A1", "A2");
        model.addAttribute("children", childrenRepository.findByGruppaNames(groupNames));
        return "starwije/list";
    }
    @GetMapping("/report")
    public String report(
            @RequestParam(name = "id", required = false) Integer id,
            Model model
    ) {
        if (id != null) {
            Optional<Children> opt = childrenService.getChildById(id);
            if (opt.isPresent()) {
                model.addAttribute("children", List.of(opt.get()));
            } else {
                model.addAttribute("children", Collections.emptyList());
                model.addAttribute("error", "Ребёнок с ID " + id + " не найден");
            }
        } else {
            // если id не передали — покажем всех
            List<Children> all = childrenService.getAll();
            model.addAttribute("children", all);
        }
        return "children-report";  // имя вашего Thymeleaf-шаблона
    }

    @PostMapping("/report")
    public String findChildById(@RequestParam("childId") Integer childId, Model model) {
        Optional<Children> child = childrenService.getById(childId);
        if (child.isPresent()) {
            model.addAttribute("children", List.of(child.get()));  // список с одним ребенком
        } else {
            model.addAttribute("children", List.of());  // пустой список
            model.addAttribute("error", "Ребенок с ID " + childId + " не найден");
        }
        return "children-report";
    }
}
