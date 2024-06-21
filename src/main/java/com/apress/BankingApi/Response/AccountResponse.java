package com.apress.BankingApi.Response;

import com.apress.BankingApi.Exception.ResourceNotFoundException;
import com.apress.BankingApi.Models.Account;
import com.apress.BankingApi.Services.AccountService;
import com.apress.BankingApi.dto.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class AccountResponse {
    @Autowired
    AccountService accountService;

    public ResponseEntity<?> createAccount(Account account,long customerId ){
        try{
            Account createdAccount = accountService.createAccount(account,customerId);

            Body body = new Body();
            body.setData(createdAccount);
            body.setCode(HttpStatus.CREATED.value());
            body.setMessage("Account created");

            return ResponseEntity.ok(body);
        }catch(Exception exception){
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("error fetching creating customers account");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    public ResponseEntity<?> getAllAccounts(){
        try{
            Body body = new Body();
            body.setData(accountService.getAllAccounts());
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Success");

            return new ResponseEntity<>(body, HttpStatus.OK);
        }catch(Exception exception){
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching accounts");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<?> getAccountById(Long accountId) {
        try {
            Optional<Account> account = accountService.getAccountById(accountId);

            if (account.isPresent()) {
                Body body = new Body();
                body.setData(account.get());
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Account retrieval success");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Error fetching account");

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error fetching account");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateAccount(long id,Account account) {
        try {
            Account editedAccount = accountService.updateAccount(id, account);

            if (editedAccount != null) {
                Body body = new Body();
                body.setData(editedAccount);
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Account updated successfully");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Account not found");

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } catch (ResourceNotFoundException e) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage(e.getMessage());

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error updating account");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteAccount(Long accountId) {
        try {
            accountService.deleteAccount(accountId);
            Body body = new Body();
            body.setCode(HttpStatus.NO_CONTENT.value());
            body.setMessage("Account deleted successfully");

            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage(e.getMessage());

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            Body body = new Body();
//            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            body.setMessage("Error deleting account");
//
//            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> getAccountsByCustomerId(Long customerId) {
        try {
            Optional<Account> account = accountService.getAccountsByCustomerId(customerId);

            if (account.isPresent()) {
                Body body = new Body();
                body.setData(account.get());
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Account retrieval success");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Error fetching account");

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error fetching account");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
