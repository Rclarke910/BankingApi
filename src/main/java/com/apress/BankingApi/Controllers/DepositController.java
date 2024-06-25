package com.apress.BankingApi.Controllers;


import com.apress.BankingApi.Models.Deposit;
import com.apress.BankingApi.Response.DepositResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class DepositController {

    @Autowired
    private DepositResponse depositResponse;

    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDeposits(@PathVariable Long accountId) {
        return new ResponseEntity<>(depositResponse.getAllDeposits(), HttpStatus.OK);
    }
    @RequestMapping(value = "/deposits/{depositId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepositById(@PathVariable Long depositId) {
        return new ResponseEntity<>(depositResponse.getDepositById(depositId), HttpStatus.OK);
    }
    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.POST)
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @Valid @RequestBody Deposit deposit) {
        deposit.setPayeeId(accountId);
        return new ResponseEntity<>(depositResponse.createDeposit(deposit), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/deposits/{depositId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long depositId) {
        return new ResponseEntity<>(depositResponse.updateDeposit(deposit, depositId), HttpStatus.ACCEPTED);

    }
    @RequestMapping(value = "/deposits/{depositId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId) {
        return new ResponseEntity<>(depositResponse.deleteDeposit(depositId), HttpStatus.OK);
    }
}


