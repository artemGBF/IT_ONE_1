package ru.vk.competition.minbenchmark.service;

import ru.vk.competition.minbenchmark.dao.ReportRepo;
import ru.vk.competition.minbenchmark.dao.TableDAO;
import ru.vk.competition.minbenchmark.dao.TableRepo;
import ru.vk.competition.minbenchmark.dto.ReportGetColumnDTO;
import ru.vk.competition.minbenchmark.dto.ReportGetDTO;
import ru.vk.competition.minbenchmark.dto.ReportGetTableDTO;
import ru.vk.competition.minbenchmark.entity.Column;
import ru.vk.competition.minbenchmark.entity.Report;
import ru.vk.competition.minbenchmark.entity.Table;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vk.competition.minbenchmark.entity.TableForReport;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepo reportRepo;
    private final TableRepo tableRepo;
    private final TableDAO tableDAO;

    public ReportGetDTO getReport(Integer reportId) {
        Optional<Report> reportOptional = reportRepo.findById(reportId);
        if (reportOptional.isEmpty()) {
            return null;
        }

        Report report = reportOptional.get();

        ReportGetDTO dto = new ReportGetDTO();
        dto.setReportId(reportId);
        dto.setTableAmount(report.getTableAmount());
        dto.setTables(report.getTables().stream()
                .map(table -> {
                    Long count = tableDAO.countRows(table.getTableName());
                    Table t = tableRepo.findById(table.getTableName()).get();
                    List<ReportGetColumnDTO> newColumns = t.getColumnInfos().stream()
                            .map(column -> new ReportGetColumnDTO(column.getTitle(), column.getType(), String.valueOf(count)))
                            .collect(Collectors.toList());
                    return new ReportGetTableDTO(table.getTableName(), newColumns);
                })
                .collect(Collectors.toList()));

        return dto;
    }

    public boolean createReport(Report report) {
        List<TableForReport> tables = report.getTables();
        if (report.getTableAmount() != tables.size()) {
            return false;
        }
        Optional<Report> optionalReport = reportRepo.findById(report.getReportId());
        if (optionalReport.isPresent()) {
            return false;
        }
        for (int i = 0; i < tables.size(); i++) {
            TableForReport table = tables.get(i);
            Optional<Table> optionalTable = tableRepo.findById(table.getTableName());
            if (optionalTable.isEmpty()) {
                return false;
            }
            List<Column> columnInfos = optionalTable.get().getColumnInfos();
            List<Column> columns = table.getColumns();
            if (columnInfos.size() != columns.size()) {
                return false;
            }
            for (int j = 0; j < columns.size(); j++) {
                boolean wasFound = false;
                for (int k = 0; k < columnInfos.size(); k++) {
                    if (columns.get(j).equals(columnInfos.get(k))) {
                        wasFound = true;
                        if (!columns.get(j).getType().equals(columnInfos.get(k).getType())) {
                            return false;
                        }
                        break;
                    }
                }
                if (!wasFound) {
                    return false;
                }
            }
        }

        reportRepo.save(report);
        return true;
    }
}
