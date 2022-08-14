package ru.vk.competition.minbenchmark.dao;

import ru.vk.competition.minbenchmark.entity.TableQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface TableQueryRepo extends JpaRepository<TableQuery, Integer> {
    @Modifying
    void deleteByQueryId(Integer queryId);

    List<TableQuery> findAllByTableName(String tableName);

    Optional<TableQuery> findByQueryId(Integer queryId);


}
