package ru.vk.competition.minbenchmark.dto;

import lombok.Data;
import ru.vk.competition.minbenchmark.entity.TableForReport;

import java.util.List;

@Data
public class ReportDTO {
    private Object reportId;
    private Integer tableAmount;
    private List<TableForReport> tables;
}
