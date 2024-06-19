package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Deposit;
import com.apress.BankingApi.Repos.AccountRepository;
import com.apress.BankingApi.Repos.DepositRepository;
import com.apress.BankingApi.Services.DepositService;
import jakarta.validation.Valid;
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
@RequestMapping("/accounts/{accountId}/deposits")
public class DepositController {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<List<Deposit>> getAllDeposits(@PathVariable Long accountId) {
        List<Deposit> deposits = depositRepository.findDepositById(accountId);
        return ResponseEntity.ok(deposits);
    }

    @GetMapping("/{depositId}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable Long accountId, @PathVariable Long depositId) {
        Optional<Deposit> depositOptional = depositRepository.findById(depositId);
        if (depositOptional.isPresent() && depositOptional.get().getPayeeId().equals(accountId)) {
            return ResponseEntity.ok(depositOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Deposit> createDeposit(@PathVariable Long accountId, @Valid @RequestBody Deposit deposit) {
        if (!accountRepository.existsById(accountId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        deposit.setPayeeId(accountId);
        Deposit savedDeposit = depositRepository.save(deposit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDeposit);
    }

    @PutMapping("/{depositId}")
    public ResponseEntity<Deposit> updateDeposit(@PathVariable Long accountId, @PathVariable Long depositId, @Valid @RequestBody Deposit deposit) {
        Optional<Deposit> existingDepositOptional = depositRepository.findById(depositId);

        if (existingDepositOptional.isPresent() && existingDepositOptional.get().getPayeeId().equals(accountId)) {
            Deposit existingDeposit = existingDepositOptional.get();


            existingDeposit.setAmount(deposit.getAmount());
            existingDeposit.setTransactionDate(deposit.getTransactionDate());
            existingDeposit.setDescription(deposit.getDescription());

            depositRepository.save(existingDeposit);
            return ResponseEntity.accepted().body(existingDeposit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{depositId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long accountId, @PathVariable Long depositId) {
        Optional<Deposit> depositOptional = depositRepository.findById(depositId);
        if (depositOptional.isPresent() && depositOptional.get().getPayeeId().equals(accountId)) {
            depositRepository.deleteById(depositId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}