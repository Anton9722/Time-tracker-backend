package com.timetrackerbackend.timetrackerbackend.services;


import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.timetrackerbackend.timetrackerbackend.models.Account;
import com.timetrackerbackend.timetrackerbackend.models.Task;

@Service
public class TaskService {
    
    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    //fixa valedering
    //create task
    public Task createTask(Task task, String accountId) {
        
        mongoOperations.insert(task);

        //find the account that created the task and then adds the task to its tasklist
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(accountId));
        Account accountToGetTask = mongoOperations.findOne(query, Account.class);

        //adds task to accounts taskList and the saves the changes
        accountToGetTask.getTaskList().add(task);
        mongoOperations.save(accountToGetTask);
        
        return task;
    }
    //get a task 
    public Task getTaskById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, Task.class);
    }

    //edit taskTime
    public Task editTaskTime(String id, Task task) {

        //find task to edit
        Query query = Query.query(Criteria.where("id").is(id));

        //what we want to change
        Update update = Update.update("taskTime", task.getTaskTime());

        //here we change it
        mongoOperations.updateFirst(query, update, Task.class);
        
        return mongoOperations.findById(id, Task.class);

    }

}
