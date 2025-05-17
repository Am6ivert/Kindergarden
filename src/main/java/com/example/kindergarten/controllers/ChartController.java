package com.example.kindergarten.controllers;

import com.example.kindergarten.services.ChartService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ChartController {

    private final ChartService chartService;
    @GetMapping("/charts")
    public String showChartsPage() {
        return "charts";  // имя файла без .html
    }

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    // График для кружков
    @GetMapping("/chart/kruzhok")
    public ResponseEntity<byte[]> getKruzhokChart() throws IOException {
        byte[] chart = chartService.generateKruzhokChart();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        // убираем headers.setContentDispositionFormData
        return new ResponseEntity<>(chart, headers, HttpStatus.OK);
    }

    // График для групп
    @GetMapping("/chart/gruppa")
    public ResponseEntity<byte[]> getGruppaChart() throws IOException {
        byte[] chart = chartService.generateGruppaChart();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(chart, headers, HttpStatus.OK);
    }

    // График для национальностей
    @GetMapping("/chart/nationality")
    public ResponseEntity<byte[]> getNationalityChart() throws IOException {
        byte[] chart = chartService.generateNationalityChart();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(chart, headers, HttpStatus.OK);
    }
}
