package com.timetrackerbackend.timetrackerbackend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.timetrackerbackend.timetrackerbackend.models.Account;
import com.timetrackerbackend.timetrackerbackend.models.Task;
import com.timetrackerbackend.timetrackerbackend.services.AccountService;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {
    
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //create new account
    @PostMapping("/account/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    //get an account by id
    @GetMapping("/account/get/{id}")
    public Account getAccountById(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    //get an account by username
    @GetMapping("/account/getByUsername/{username}")
    public Account getAccountByUsername(@PathVariable String username) {
        return accountService.getAccountByUsername(username);
    }

    //get all tasks in account
    @GetMapping("account/getalltasks/{id}")
    public List<Task> getTaskListById(@PathVariable String id) {
        return accountService.getTaskListById(id);
    }

    //check account login 
    @GetMapping("account/login/{username}/{password}")
    public Boolean isLoginInfoCorrect(@PathVariable String username, @PathVariable String password) {
        return accountService.isLoginInfoCorrect(username, password);
    }

    //add task to saved task names
    @PostMapping("account/addtask/{taskName}/{accountId}")
    public String saveTaskName(@PathVariable String taskName, @PathVariable String accountId) {
        return accountService.saveTaskName(taskName, accountId);
    }

    //get all account (admin function)
    @GetMapping("account/getallaccounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

}
