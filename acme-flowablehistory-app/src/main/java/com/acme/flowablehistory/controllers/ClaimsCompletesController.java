package com.acme.flowablehistory.controllers;

import com.acme.flowablehistory.models.ClaimsCompletes;
import com.acme.flowablehistory.services.ClaimsCompletesService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ievgenii Bespal
 */

@RestController
@RequestMapping("/task-history/")
public class ClaimsCompletesController {
    final
    ClaimsCompletesService claimsCompletesService;

    public ClaimsCompletesController(ClaimsCompletesService claimsCompletesService) {
        this.claimsCompletesService = claimsCompletesService;
    }

    @GetMapping(value = "/claims-completed/{taskId}", produces = "application/json")
    public List<ClaimsCompletes> getClaimsCompletesById(@ApiParam(name = "taskId") @PathVariable String taskId) {
        return claimsCompletesService.getClaimsCompletesById(taskId);
    }
}
