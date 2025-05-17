package com.example.kindergarten.controllers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    // Метод для чтения Excel
    public List<List<String>> readExcel(File file) throws IOException {
        List<List<String>> rows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                List<String> cells = new ArrayList<>();
                for (Cell cell : row) {
                    cell.setCellType(CellType.STRING);
                    cells.add(cell.getStringCellValue());
                }
                rows.add(cells);
            }
        }
        return rows;
    }

    // Метод для отображения отчета
    @GetMapping("/report/view")
    public String viewReport(Model model) throws IOException {
        File excelFile = new File("путь_до_твоего_файла.xlsx"); // путь до файла
        List<List<String>> table = readExcel(excelFile);
        model.addAttribute("table", table);
        return "children-report"; // имя шаблона HTML
    }
}