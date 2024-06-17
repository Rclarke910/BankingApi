package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Withdrawal;
import com.apress.BankingApi.Services.WithdrawlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class WithdrawalController {
    @Autowired
    private WithdrawlService withdrawlService;

    @RequestMapping(value = "/accounts/{accountId}/withdrawals", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Withdrawal>> getAccountsWithdrawals(){
        Iterable<Withdrawal> withdrawals = withdrawlService.getAllWithdrawals();
        return new ResponseEntity<>(withdrawlService.getAllWithdrawals(), HttpStatus.OK);
    }



}
