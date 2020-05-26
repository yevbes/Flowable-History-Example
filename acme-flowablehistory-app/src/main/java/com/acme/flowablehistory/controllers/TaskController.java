package com.acme.flowablehistory.controllers;

import com.acme.flowablehistory.models.Task;
import com.acme.flowablehistory.services.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ievgenii Bespal
 */

@RestController
@RequestMapping("/task-history/")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/tasks", produces = "application/json")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }
}
