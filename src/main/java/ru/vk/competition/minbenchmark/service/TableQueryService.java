package ru.vk.competition.minbenchmark.service;

import ru.vk.competition.minbenchmark.dao.QueryDAO;
import ru.vk.competition.minbenchmark.dao.TableQueryRepo;
import ru.vk.competition.minbenchmark.dao.TableRepo;
import ru.vk.competition.minbenchmark.entity.TableQuery;
import ru.vk.competition.minbenchmark.entity.Table;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TableQueryService {
    private final TableQueryRepo queryRepo;
    private final TableRepo tableRepo;
    private final QueryDAO queryDAO;

    public boolean createQuery(TableQuery query) {
        if (query.getQueryId() == null) {
            return false;
        }
        Optional<TableQuery> optional = queryRepo.findByQueryId(query.getQueryId());
        if (optional.isPresent()) {
            return false;
        }
        Optional<Table> table = tableRepo.findById(query.getTableName());
        if (table.isEmpty()) {
            return false;
        }

        queryRepo.save(query);
        return true;
    }

    public boolean updateQuery(TableQuery query) {
        Optional<TableQuery> optionalQuery = queryRepo.findById(query.getQueryId());
        if (optionalQuery.isEmpty()) {
            return false;
        }
        Optional<Table> table = tableRepo.findById(query.getTableName());
        if (table.isEmpty()) {
            return false;
        }
        if(!query.getQuery().contains(query.getTableName())){
            return false;
        }

        TableQuery openedQuery = optionalQuery.get();
        openedQuery.setQuery(query.getQuery());
        openedQuery.setTableName(query.getTableName());
        queryRepo.save(openedQuery);
        return true;
    }

    @Transactional
    public boolean deleteQuery(Integer id) {
        Optional<TableQuery> query = queryRepo.findByQueryId(id);
        if (query.isEmpty()) {
            return false;
        }
        queryRepo.deleteByQueryId(id);
        return true;
    }

    public boolean executeQuery(Integer queryId) {
        try {
            TableQuery query = queryRepo.findById(queryId).get();
            if (!query.getQuery().contains(query.getTableName())){
                return false;
            }
            queryDAO.executeQuery(query.getQuery());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TableQuery> getAllByTableName(String name) {
        return queryRepo.findAllByTableName(name);
    }

    public Optional<TableQuery> getById(Integer id) {
        return queryRepo.findByQueryId(id);
    }

    public List<TableQuery> getAll() {
        return queryRepo.findAll();
    }

}
