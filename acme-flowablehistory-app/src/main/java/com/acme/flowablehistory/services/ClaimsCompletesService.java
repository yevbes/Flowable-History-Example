package com.acme.flowablehistory.services;

import com.acme.flowablehistory.models.ClaimsCompletes;
import org.flowable.engine.HistoryService;
import org.flowable.task.api.history.HistoricTaskLogEntryQuery;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ievgenii Bespal
 */

@Service
public class ClaimsCompletesService {

    final
    HistoryService historyService;

    public ClaimsCompletesService(HistoryService historyService) {
        this.historyService = historyService;
    }

    public List<ClaimsCompletes> getClaimsCompletesById(String taskId) {
        HistoricTaskLogEntryQuery historicTaskLogEntryQuery = historyService.createHistoricTaskLogEntryQuery();

        return historicTaskLogEntryQuery.list().stream().filter(
                item -> item.getTaskId().equals(taskId)
        ).map(
                item -> new ClaimsCompletes(
                        item.getTaskId(),
                        new Timestamp(item.getTimeStamp().getTime()),
                        item.getType(),
                        item.getData(),
                        item.getUserId()
                )
        ).collect(Collectors.toList());
    }
}
