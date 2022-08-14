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
@Table(name = "single_queries")
public class SingleQuery {
    @Id
    @Column(nullable = false)
    private Integer queryId;
    private String query;
}
