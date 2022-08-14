package ru.vk.competition.minbenchmark.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class QueryDAO {
    private NamedParameterJdbcTemplate template;

    public void executeQuery(String query) {
        try {
            template.query(query, rs -> {
            });
        } catch (UncategorizedSQLException e) {
            template.execute(query, (PreparedStatementCallback<Object>) ps -> {
                ps.execute();
                return "result";
            });
        }
    }
}
