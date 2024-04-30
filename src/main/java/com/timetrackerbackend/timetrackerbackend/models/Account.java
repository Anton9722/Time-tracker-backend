package com.timetrackerbackend.timetrackerbackend.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Document(collection = "Accounts")
public class Account {
    @Id
    public String id;
    public List<String> savedTaskNames;
    public List<Task> taskList;
    public String username;
    public String passwordHash;

    public Account(String id, List<String> savedTaskNames, List<Task> taskList, String username, String password) {
        this.id = id;
        this.savedTaskNames = savedTaskNames != null ? savedTaskNames : new ArrayList<>();
        this.taskList = taskList != null ? taskList : new ArrayList<>();
        this.username = username;
        setPassword(password);
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.passwordHash = passwordEncoder.encode(password);
    }

    public boolean isPasswordCorrect(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, this.passwordHash);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getSavedTaskNames() {
        return savedTaskNames;
    }

    public void setSavedTaskNames(List<String> savedTaskNames) {
        this.savedTaskNames = savedTaskNames;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

}
