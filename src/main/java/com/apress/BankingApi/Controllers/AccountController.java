package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Account;
import com.apress.BankingApi.Response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountResponse accountResponse;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {
        return new ResponseEntity<>(accountResponse.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        return new ResponseEntity<>(accountResponse.getAccountById(accountId), HttpStatus.OK);
    }

    @GetMapping("/customers/{customerid}/accounts")
    public ResponseEntity<?> getAccountsByCustomerId(@PathVariable long customerid) {
        return accountResponse.getAccountsByCustomerId(customerid);
    }


    @PostMapping("customers/{customerId}/accounts")
    public ResponseEntity<?> addAccount(@RequestBody Account account, @PathVariable long customerId){
        return accountResponse.createAccount(account,customerId);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable int accountId, @RequestBody Account account){
        return accountResponse.updateAccount(accountId,account);
    }

    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable long id){
        return accountResponse.deleteAccount(id);
    }

}
