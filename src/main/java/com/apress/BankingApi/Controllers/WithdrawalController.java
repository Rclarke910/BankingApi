package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Withdrawal;
import com.apress.BankingApi.Services.WithdrawalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class WithdrawalController {
    @Autowired
    private WithdrawalService withdrawalService;

    @RequestMapping(value = "/accounts/{accountId}/withdrawals", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Withdrawal>> getAccountsWithdrawals(@PathVariable Long accountID){
        withdrawalService.verifyAccount(accountID);
        Iterable<Withdrawal> withdrawals = withdrawalService.getAllWithdrawals();
        return new ResponseEntity<>(withdrawalService.getAllWithdrawals(), HttpStatus.OK);
    }
    @RequestMapping(value="/withdrawals/{withdrawalID}", method=RequestMethod.GET)
    public ResponseEntity<?> getWithdrawal(@PathVariable Long withdrawalId){
        Optional<Withdrawal> w = withdrawalService.getWithdrawalByID(withdrawalId);
        return new ResponseEntity<> (w, HttpStatus.OK);
    }
    @RequestMapping(value = "accounts/{accountID}/withdrawals", method = RequestMethod.POST)
    public ResponseEntity<?> createWithdrawal(@Valid @RequestBody Withdrawal withdrawal) {
        withdrawalService.createWithdrawal(withdrawal);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newWithdrawalUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(withdrawal.getId())
                .toUri();
        responseHeaders.setLocation(newWithdrawalUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    @RequestMapping(value="/withdrawals/{withdrawalID}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable Long withdrawalID) {
        withdrawalService.updateWithdrawal(withdrawalID, withdrawal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/withdrawals/{withdrawalID}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId) {
        withdrawalService.deleteWithdrawalById(withdrawalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
