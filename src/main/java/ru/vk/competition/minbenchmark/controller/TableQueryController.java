package ru.vk.competition.minbenchmark.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vk.competition.minbenchmark.entity.TableQuery;
import ru.vk.competition.minbenchmark.mappers.QueryMapper;
import ru.vk.competition.minbenchmark.service.TableQueryService;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api/table-query")
@AllArgsConstructor
public class TableQueryController {
    private final TableQueryService queryService;
    private final QueryMapper queryMapper;

    @GetMapping("/execute-table-query-by-id/{obj}")
    public ResponseEntity<Void> executeQuery(@PathVariable Object obj) {
        try {
            Integer id = Integer.valueOf(String.valueOf(obj));
            boolean b = queryService.executeQuery(id);
            if (b) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/get-all-queries-by-table-name/{name}")
    public ResponseEntity<List<TableQuery>> getAllByTableName(@PathVariable String name) {
        List<TableQuery> queries = queryService.getAllByTableName(name);
        return new ResponseEntity<>(queries, HttpStatus.OK);
    }

    @GetMapping("/get-table-query-by-id/{obj}")
    public ResponseEntity<TableQuery> getById(@PathVariable Object obj) {
        Integer id = Integer.valueOf(String.valueOf(obj));
        return new ResponseEntity<>(queryService.getById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/get-all-table-queries")
    public ResponseEntity<List<TableQuery>> getAll() {
        List<TableQuery> queries = queryService.getAll();
        return new ResponseEntity<>(queries, HttpStatus.OK);
    }

    @PostMapping("/add-new-query-to-table")
    public ResponseEntity<Void> createQuery(@RequestBody Object obj) {
        try {
            TableQuery query = queryMapper.toEntity((LinkedHashMap<String, Object>) obj);
            if (query.getQuery().length() > 120 || query.getTableName().length() > 50) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            boolean b = queryService.createQuery(query);
            if (b) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/modify-query-in-table")
    public ResponseEntity<Void> updateQuery(@RequestBody Object obj) {
        try {
            TableQuery query = queryMapper.toEntity((LinkedHashMap<String, Object>) obj);
            if (query.getQuery().length() > 120 || query.getTableName().length() > 50) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            boolean b = queryService.updateQuery(query);
            if (b) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete-table-query-by-id/{obj}")
    public ResponseEntity<Void> deleteQuery(@PathVariable Object obj) {
        try {
            Integer id = Integer.valueOf(String.valueOf(obj));
            boolean b = queryService.deleteQuery(id);
            if (b) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
