package ru.vk.competition.minbenchmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReportGetTableDTO {
    private String tableName;
    private List<ReportGetColumnDTO> columns;
}
