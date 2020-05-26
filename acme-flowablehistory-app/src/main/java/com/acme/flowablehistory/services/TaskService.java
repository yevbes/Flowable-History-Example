package com.acme.flowablehistory.services;

import com.acme.flowablehistory.models.Task;
import org.flowable.engine.HistoryService;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ievgenii Bespal
 */

@Service
public class TaskService {

    final
    HistoryService historyService;

    public TaskService(HistoryService historyService) {
        this.historyService = historyService;
    }

    public List<Task> getTasks() {

       HistoricTaskInstanceQuery historicTaskInstanceQuery =
                historyService.createHistoricTaskInstanceQuery();

        return historicTaskInstanceQuery
                .list()
                .stream()
                .map(
                        item -> new Task(item.getId())
                )
                .collect(Collectors.toList());
    }
}
