package com.timetrackerbackend.timetrackerbackend.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Document(collection = "Accounts")
public class Account {
    @Id
    private String id;
    private List<String> savedTaskNames;
    private List<Task> taskList;
    private String username;
    private String password;
    private Boolean isAdmin;

    public Account(String id, List<String> savedTaskNames, List<Task> taskList, String username, Boolean isAdmin) {
        this.id = id;
        this.savedTaskNames = savedTaskNames != null ? savedTaskNames : new ArrayList<>();
        this.taskList = taskList != null ? taskList : new ArrayList<>();
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public boolean isPasswordCorrect(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, getPassword());
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

    public String getPassword() {
        return password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
