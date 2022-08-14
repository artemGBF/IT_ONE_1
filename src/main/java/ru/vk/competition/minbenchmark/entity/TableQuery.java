package ru.vk.competition.minbenchmark.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_queries")
public class TableQuery {
    @Id
    @Column(nullable = false)
    private Integer queryId;
    private String tableName;
    private String query;
}
