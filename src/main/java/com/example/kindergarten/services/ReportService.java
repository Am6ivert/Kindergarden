package com.example.kindergarten.services;

import com.example.kindergarten.entities.Children;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    public ByteArrayInputStream generateChildrenReport(List<Children> childrenList) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Children Report");

            // Заголовки
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ФИО");
            header.createCell(1).setCellValue("Дата рождения");
            header.createCell(2).setCellValue("Группа");
            header.createCell(3).setCellValue("Телефон отца");
            header.createCell(4).setCellValue("Телефон мамы");
            header.createCell(5).setCellValue("Группа");
            header.createCell(6).setCellValue("Кружок");
            header.createCell(7).setCellValue("Национальность");

            int rowIdx = 1;
            for (Children child : childrenList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(child.getFio());
                row.createCell(1).setCellValue(child.getDateborn().toString());
                row.createCell(2).setCellValue(child.getAdres());
                row.createCell(3).setCellValue(child.getTelPap());
                row.createCell(4).setCellValue(child.getTelMam());
                row.createCell(5).setCellValue(child.getGruppa().getGruppa());
                row.createCell(6).setCellValue(child.getKruzhok().getKruzhok());
                row.createCell(7).setCellValue(child.getNationality().getNationality());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}

