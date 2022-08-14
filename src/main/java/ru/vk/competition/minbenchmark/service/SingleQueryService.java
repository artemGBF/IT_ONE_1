package ru.vk.competition.minbenchmark.service;

import ru.vk.competition.minbenchmark.dao.QueryDAO;
import ru.vk.competition.minbenchmark.dao.SingleQueryRepo;
import ru.vk.competition.minbenchmark.dao.TableRepo;
import ru.vk.competition.minbenchmark.entity.SingleQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SingleQueryService {
    private final SingleQueryRepo queryRepo;
    private final TableRepo tableRepo;
    private final QueryDAO queryDAO;

    public boolean createQuery(SingleQuery query) {
        if (query.getQueryId() == null) {
            return false;
        }
        Optional<SingleQuery> optional = queryRepo.findByQueryId(query.getQueryId());
        if (optional.isPresent()) {
            return false;
        }

        queryRepo.save(query);
        return true;
    }

    public boolean updateQuery(SingleQuery query) {
        Optional<SingleQuery> optionalQuery = queryRepo.findById(query.getQueryId());
        if (optionalQuery.isEmpty()) {
            return false;
        }

        SingleQuery openedQuery = optionalQuery.get();
        openedQuery.setQuery(query.getQuery());
        queryRepo.save(openedQuery);
        return true;
    }

    @Transactional
    public boolean deleteQuery(Integer id) {
        Optional<SingleQuery> query = queryRepo.findByQueryId(id);
        if (query.isEmpty()) {
            return false;
        }
        queryRepo.deleteByQueryId(id);
        return true;
    }

    public boolean executeQuery(Integer queryId) {
        try {
            SingleQuery query = queryRepo.findById(queryId).get();
            queryDAO.executeQuery(query.getQuery());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<SingleQuery> getById(Integer id) {
        return queryRepo.findByQueryId(id);
    }

    public List<SingleQuery> getAll() {
        return queryRepo.findAll();
    }

}
