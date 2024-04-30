package com.timetrackerbackend.timetrackerbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.timetrackerbackend.timetrackerbackend.models.Task;
import com.timetrackerbackend.timetrackerbackend.services.TaskService;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    //lägg till ny task
    @PostMapping("/task/create/{accountId}")
    public Task createTask(@RequestBody Task task, @PathVariable String accountId) {
        return taskService.createTask(task, accountId);
    }
}
