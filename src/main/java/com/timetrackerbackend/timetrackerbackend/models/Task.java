package com.timetrackerbackend.timetrackerbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tasks")
public class Task {
    @Id
    private String id;
    private String taskName;
    private int taskTime;
    private String createdOnWeek;

    public Task(String id, String taskName, int taskTime, String createdOnWeek) {
        this.id = id;
        this.taskName = taskName;
        this.taskTime = taskTime;
        this.createdOnWeek = createdOnWeek;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(int taskTime) {
        this.taskTime = taskTime;
    }

    public String getCreatedOnWeek() {
        return createdOnWeek;
    }

    public void setCreatedOnWeek(String createdOnWeek) {
        this.createdOnWeek = createdOnWeek;
    }
    
}
