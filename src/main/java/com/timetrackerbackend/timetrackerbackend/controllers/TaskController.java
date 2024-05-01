package com.timetrackerbackend.timetrackerbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    //create a task
    @PostMapping("/task/create/{accountId}")
    public Task createTask(@RequestBody Task task, @PathVariable String accountId) {
        return taskService.createTask(task, accountId);
    }

    //get a task by id
    @GetMapping("/task/get/{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    //edit taskTime
    @PatchMapping("/task/patch/{id}")
    public Task editTaskTime(@PathVariable String id, @RequestBody Task task) {
        return taskService.editTaskTime(id, task);
    }
}
