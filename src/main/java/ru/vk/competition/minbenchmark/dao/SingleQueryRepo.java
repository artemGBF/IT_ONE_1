package ru.vk.competition.minbenchmark.dao;

import ru.vk.competition.minbenchmark.entity.SingleQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface SingleQueryRepo extends JpaRepository<SingleQuery, Integer> {
    @Modifying
    void deleteByQueryId(Integer queryId);

    Optional<SingleQuery> findByQueryId(Integer queryId);
}
