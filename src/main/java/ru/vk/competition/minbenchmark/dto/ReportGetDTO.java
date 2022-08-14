package ru.vk.competition.minbenchmark.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportGetDTO {
    private Integer reportId;
    private Integer tableAmount;
    private List<ReportGetTableDTO> tables;
}
