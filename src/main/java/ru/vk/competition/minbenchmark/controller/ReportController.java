package ru.vk.competition.minbenchmark.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vk.competition.minbenchmark.dto.ReportDTO;
import ru.vk.competition.minbenchmark.dto.ReportGetDTO;
import ru.vk.competition.minbenchmark.entity.Report;
import ru.vk.competition.minbenchmark.service.ReportService;

@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/get-report-by-id/{obj}")
    public ResponseEntity<ReportGetDTO> getReport(@PathVariable Object obj) {
        try {
            Integer id = Integer.valueOf(String.valueOf(obj));
            ReportGetDTO b = reportService.getReport(id);
            if (b != null) {
                return new ResponseEntity<>(b, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/create-report")
    public ResponseEntity<Void> createReport(@RequestBody ReportDTO dto) {
        try {
            Integer reportId = Integer.valueOf(String.valueOf(dto.getReportId()));
            boolean b = reportService.createReport(new Report(
                    reportId,
                    dto.getTableAmount(),
                    dto.getTables()
            ));
            if (b) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
