package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Withdrawal;
import com.apress.BankingApi.Response.WithdrawalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class WithdrawalController {
    @Autowired
    private WithdrawalResponse withdrawalResponse;

    @RequestMapping(value = "/accounts/{accountID}/withdrawals", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountsWithdrawals(@PathVariable Long accountID){
        return new ResponseEntity<>(withdrawalResponse.getAccountWithdrawals(accountID), HttpStatus.OK);
    }

    @RequestMapping(value="/withdrawals/{withdrawalID}", method=RequestMethod.GET)
    public ResponseEntity<?> getWithdrawalByID(@PathVariable Long withdrawalId){
        return new ResponseEntity<> (withdrawalResponse.getWithdrawalById(withdrawalId), HttpStatus.OK);
    }

    @RequestMapping(value = "accounts/{accountID}/withdrawals", method = RequestMethod.POST)
    public ResponseEntity<?> createWithdrawal(@Valid @RequestBody Withdrawal withdrawal,@PathVariable Long accountID) {
        return new ResponseEntity<>(withdrawalResponse.createWithdrawal(withdrawal,accountID), HttpStatus.CREATED);
    }

    @RequestMapping(value="/withdrawals/{withdrawalID}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable Long withdrawalID) {
        return new ResponseEntity<>(withdrawalResponse.updateWithdrawal(withdrawal,withdrawalID), HttpStatus.OK);
    }

    @RequestMapping(value="/withdrawals/{withdrawalID}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId) {
        return new ResponseEntity<>(withdrawalResponse.deleteWithdrawal(withdrawalId),HttpStatus.OK);
    }

}
