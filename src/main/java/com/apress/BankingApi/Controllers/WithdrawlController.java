package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Withdrawl;
import com.apress.BankingApi.Repos.WithdrawlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawlController {
    @Autowired
    private WithdrawlRepository withdrawlRepository;

}
