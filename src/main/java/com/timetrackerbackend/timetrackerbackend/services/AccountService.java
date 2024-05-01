package com.timetrackerbackend.timetrackerbackend.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.timetrackerbackend.timetrackerbackend.models.Account;
import com.timetrackerbackend.timetrackerbackend.models.Task;

@Service
public class AccountService {

    private final MongoOperations mongoOperations;

    public AccountService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    //create new account
    public Account createAccount(Account account) {
        return mongoOperations.insert(account);
    }

    //get account from id
    public Account getAccountById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, Account.class);
    }

    //get all tasks in account
    public List<Task> getTaskListById(String id) {
        Account account = getAccountById(id);
        return account.getTaskList();
    }
    
}
