package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Deposit;
import com.apress.BankingApi.Repos.DepositRepository;
import com.apress.BankingApi.Services.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class DepositController {

    private static final Logger log = LoggerFactory.getLogger(SpringApplication.class);
    @Autowired
    private DepositService depositService;


    @GetMapping("/deposits")
    public Iterable<Deposit> getAllDeposits() {
        return depositService.getAllDeposits();
    }

    @GetMapping("/deposits/{id}")
    public Optional<Deposit> getDepositById(@PathVariable long id) {
        return depositService.getDepositById(id);
    }

}