package ru.vk.competition.minbenchmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportGetColumnDTO {
    private String title;
    private String type;
    private String size;
}
