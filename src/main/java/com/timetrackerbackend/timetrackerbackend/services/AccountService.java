package com.timetrackerbackend.timetrackerbackend.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createAccount(Account account) {
        String username = account.getUsername();
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        if(mongoOperations.findOne(query, Account.class) == null) {
            mongoOperations.insert(account);
            return ResponseEntity.status(HttpStatus.OK).body("Account was created");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("An account with that username alredy exist");
        }
    }

    //get account from id
    public Account getAccountById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, Account.class);
    }

    //get account from username
    public Account getAccountByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoOperations.findOne(query, Account.class);
    }

    //get all tasks in account
    public List<Task> getTaskListById(String id) {
        Account account = getAccountById(id);
        return account.getTaskList();
    }
    //validate login information
    public Boolean isLoginInfoCorrect(String username, String password) {
        Query usernameQuery = new Query();
        usernameQuery.addCriteria(Criteria.where("username").is(username));
        Account account = mongoOperations.findOne(usernameQuery, Account.class);

        return account.isPasswordCorrect(password);
    }
    //save task name
    public String saveTaskName(String taskName, String accountId) {
        
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(accountId));
        Account accountToSaveTaskName = mongoOperations.findOne(query, Account.class);

        accountToSaveTaskName.getSavedTaskNames().add(taskName);
        mongoOperations.save(accountToSaveTaskName);

        return taskName;

    }

    public List<Account> getAllAccounts() {
        return mongoOperations.findAll(Account.class);
    }
    
}
