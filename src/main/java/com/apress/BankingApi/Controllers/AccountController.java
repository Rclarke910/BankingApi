package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Account;
import com.apress.BankingApi.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts() { return accountService.getAllAccounts(); }

    @GetMapping("/accounts/{accountId}")
    public Optional<Account> getAccountById(@PathVariable int accountId) { return accountService.getAccountById(accountId); }

    @GetMapping("/customers/{customerid}/accounts")
    public List<Account> getAccountsByCustomerId(@PathVariable long customerid){
        List<Account> accounts = accountService.getAccountsByCustomerId(customerid);
        return accounts;
    }

    @PostMapping("customers/{customerId}/accounts")
    public void addAccount(@RequestBody Account account, @PathVariable long customerId){
        accountService.createAccount(account,customerId);
    }

    @PutMapping("/accounts/{accountId}")
    public void updateAccount(@PathVariable int accountId, @RequestBody Account account){
        accountService.updateAccount(accountId,account);
    }

    @DeleteMapping("/account/{accountId}")
    public void deleteAccount(@PathVariable long id){
        accountService.deleteAccount(id);
    }


}
