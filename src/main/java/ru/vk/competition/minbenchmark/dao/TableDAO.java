package ru.vk.competition.minbenchmark.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.vk.competition.minbenchmark.entity.Table;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class TableDAO {

    private final NamedParameterJdbcTemplate template;

    public Long countRows(String tableName){
        return template.queryForObject(
                "select count(*) from "+tableName,
                new MapSqlParameterSource(),
                Long.class
        );
    }

    public void createTable(Table table) {
        template.execute(
                buildQuery(table),
                (PreparedStatementCallback<Object>) ps -> {
                    ps.execute();
                    return "result";
                }
        );
    }

    public void dropTable(String name){
        template.execute("drop table "+name+";",
                (PreparedStatementCallback<Object>) ps -> {
                    ps.execute();
                    return "result";
                }
        );
    }

    private String buildQuery(Table table) {
        StringBuilder query = new StringBuilder("create table " + table.getTableName() + " (");
        String primaryKey = table.getPrimaryKey();
        table.getColumnInfos().forEach(column -> {
                    if (column.getTitle().equals(primaryKey)) {
                        query.append(column.getTitle() + " " + column.getType() + " primary key " + ", ");
                    } else {
                        query.append(column.getTitle() + " " + column.getType() + ", ");
                    }
                }
        );
        query.delete(query.length() - 2, query.length() - 1);
        query.append(");");
        return query.toString();
    }
}
