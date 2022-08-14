package ru.vk.competition.minbenchmark.dao;

import ru.vk.competition.minbenchmark.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface TableRepo extends JpaRepository<Table, String> {
    @Modifying
    void deleteByTableName(String tableName);
}
