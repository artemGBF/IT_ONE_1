package ru.vk.competition.minbenchmark.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@javax.persistence.Table(name = "tables")
public class TableForReport {
    @Id
    @javax.persistence.Column(nullable = false)
    private String tableName;
    private int columnsAmount;

    @Transient
    private List<Column> columns;
}
