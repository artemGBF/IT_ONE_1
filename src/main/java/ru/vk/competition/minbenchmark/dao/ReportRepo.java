package ru.vk.competition.minbenchmark.dao;

import ru.vk.competition.minbenchmark.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<Report, Integer> {
}
