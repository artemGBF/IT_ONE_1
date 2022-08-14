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
import ru.vk.competition.minbenchmark.entity.SingleQuery;
import ru.vk.competition.minbenchmark.mappers.QueryMapper;
import ru.vk.competition.minbenchmark.service.SingleQueryService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/single-query")
@AllArgsConstructor
public class SingleQueryController {
    private final SingleQueryService queryService;
    private final QueryMapper queryMapper;

    @GetMapping("/execute-single-query-by-id/{obj}")
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

    @GetMapping("/get-single-query-by-id/{obj}")
    public ResponseEntity<SingleQuery> getById(@PathVariable Object obj) {
        try {
            Integer id = Integer.valueOf(String.valueOf(obj));
            Optional<SingleQuery> query = queryService.getById(id);
            if (query.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(query.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-single-queries")
    public ResponseEntity<List<SingleQuery>> getAll() {
        List<SingleQuery> queries = queryService.getAll();
        return new ResponseEntity<>(queries, HttpStatus.OK);
    }

    @PostMapping("/add-new-query")
    public ResponseEntity<Void> createQuery(@RequestBody Object obj) {
        try {
            SingleQuery query = queryMapper.toSingleQuery((LinkedHashMap<String, Object>) obj);
            boolean b = queryService.createQuery(query);
            if (b) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modify-query")
    public ResponseEntity<Void> updateQuery(@RequestBody Object obj) {
        try {
            SingleQuery query = queryMapper.toSingleQuery((LinkedHashMap<String, Object>) obj);
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

    @DeleteMapping("/delete-single-query-by-id/{obj}")
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
