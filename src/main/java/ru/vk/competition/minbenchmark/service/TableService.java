package ru.vk.competition.minbenchmark.service;

import ru.vk.competition.minbenchmark.dao.TableDAO;
import ru.vk.competition.minbenchmark.dao.TableRepo;
import ru.vk.competition.minbenchmark.entity.Column;
import ru.vk.competition.minbenchmark.entity.Table;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.util.Util;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TableService {
    private final TableRepo tableRepo;
    private final TableDAO tableDAO;

    public Table getTableByName(String name) {
        Optional<Table> table = tableRepo.findById(name);
        if (table.isEmpty()) {
            return null;
        } else {
            Table tableModel = table.get();
            tableModel.setPrimaryKey(tableModel.getPrimaryKey().toLowerCase(Locale.ROOT));
            tableModel.getColumnInfos().forEach(p -> {
                p.setTitle(p.getTitle().toUpperCase(Locale.ROOT));
                p.setType(Util.transferTypes(p.getType()));
            });
            return tableModel;
        }
    }

    @Transactional
    public boolean create(Table table) {
        if (table.getColumnInfos().size() != table.getColumnsAmount()) {
            return false;
        }
        List<String> titles = table.getColumnInfos().stream().map(Column::getTitle).collect(Collectors.toList());
        if (!titles.contains(table.getPrimaryKey())) {
            return false;
        }
        Optional<Table> tableOptional = tableRepo.findById(table.getTableName());
        if (tableOptional.isPresent()) {
            return false;
        }
        try {
            tableDAO.createTable(table);
        } catch (Exception ignored) {
            return false;
        }
        tableRepo.save(table);
        return true;
    }

    @Transactional
    public boolean drop(String name) {
        try {
            tableRepo.deleteByTableName(name);
            tableDAO.dropTable(name);
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }
}
