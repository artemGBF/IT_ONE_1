package ru.vk.competition.minbenchmark.mappers;

import ru.vk.competition.minbenchmark.entity.SingleQuery;
import ru.vk.competition.minbenchmark.entity.TableQuery;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class QueryMapper {

    public TableQuery toEntity(LinkedHashMap<String, Object> obj) {
        return new TableQuery(
                Integer.valueOf(String.valueOf(obj.get("queryId"))),
                String.valueOf(obj.get("tableName")),
                String.valueOf(obj.get("query"))
        );
    }

    public SingleQuery toSingleQuery(LinkedHashMap<String, Object> obj) {
        return new SingleQuery(
                Integer.valueOf(String.valueOf(obj.get("queryId"))),
                String.valueOf(obj.get("query"))
        );
    }
}
