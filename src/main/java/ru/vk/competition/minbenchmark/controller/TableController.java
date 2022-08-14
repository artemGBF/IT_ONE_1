package ru.vk.competition.minbenchmark.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vk.competition.minbenchmark.entity.Table;
import ru.vk.competition.minbenchmark.service.TableService;

@RestController
@RequestMapping("/api/table")
@AllArgsConstructor
public class TableController {
    private final TableService tableService;

    @GetMapping("/get-table-by-name/{name}")
    @ResponseBody
    public ResponseEntity<Table> getTableByName(@PathVariable String name) {
        Table tableByName = tableService.getTableByName(name);
        if (tableByName != null) {
            return new ResponseEntity<>(tableByName, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create-table")
    public ResponseEntity<Void> create(@RequestBody Table table) {
        try {
            boolean b = tableService.create(table);
            if (b) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/drop-table/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        boolean b = tableService.drop(name);
        if (b) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
