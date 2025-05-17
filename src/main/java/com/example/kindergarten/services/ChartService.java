package com.example.kindergarten.services;

import com.example.kindergarten.entities.Children;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChartService {
    @Autowired
    private ChildrenService childrenService;

    // Генерация графика для кружков
    public byte[] generateKruzhokChart() throws IOException {
        // Подсчитаем количество детей по кружкам
        List<Children> childrenList = childrenService.getAll();
        Map<String, Integer> kruzhokData = new HashMap<>();

        for (Children child : childrenList) {
            String kruzhokName = child.getKruzhok().getKruzhok();
            kruzhokData.put(kruzhokName, kruzhokData.getOrDefault(kruzhokName, 0) + 1);
        }

        // Создаем набор данных для графика
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : kruzhokData.entrySet()) {
            dataset.addValue(entry.getValue(), "Количество детей", entry.getKey());
        }

        // Генерация графика
        JFreeChart chart = ChartFactory.createBarChart(
                "Количество детей по кружкам", // Заголовок
                "Кружки",                     // Ось X
                "Количество детей",            // Ось Y
                dataset                        // Данные
        );

        chart.setBackgroundPaint(Color.white);

        // Сохранение графика в ByteArray
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(out, chart, 800, 600);
        return out.toByteArray();
    }

    // Генерация графика для групп (аналогично)
    public byte[] generateGruppaChart() throws IOException {
        List<Children> childrenList = childrenService.getAll();
        Map<String, Integer> gruppaData = new HashMap<>();

        for (Children child : childrenList) {
            String gruppaName = child.getGruppa().getGruppa();
            gruppaData.put(gruppaName, gruppaData.getOrDefault(gruppaName, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : gruppaData.entrySet()) {
            dataset.addValue(entry.getValue(), "Количество детей", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Количество детей по группам",
                "Группы",
                "Количество детей",
                dataset
        );

        chart.setBackgroundPaint(Color.white);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(out, chart, 800, 600);
        return out.toByteArray();
    }

    // Генерация графика для национальностей (аналогично)
    public byte[] generateNationalityChart() throws IOException {
        List<Children> childrenList = childrenService.getAll();
        Map<String, Integer> nationalityData = new HashMap<>();

        for (Children child : childrenList) {
            String nationalityName = child.getNationality().getNationality();
            nationalityData.put(nationalityName, nationalityData.getOrDefault(nationalityName, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : nationalityData.entrySet()) {
            dataset.addValue(entry.getValue(), "Количество детей", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Количество детей по национальностям",
                "Национальности",
                "Количество детей",
                dataset
        );

        chart.setBackgroundPaint(Color.white);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(out, chart, 800, 600);
        return out.toByteArray();
    }
}
