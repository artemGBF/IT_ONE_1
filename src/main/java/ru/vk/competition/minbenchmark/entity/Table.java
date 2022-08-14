package ru.vk.competition.minbenchmark.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    @javax.persistence.Column(nullable = false)
    private String tableName;
    private int columnsAmount;
    private String primaryKey;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_id")
    @JsonManagedReference
    private List<Column> columnInfos;

    @Transient
    @JsonIgnore
    private List<Column> columns;
}
