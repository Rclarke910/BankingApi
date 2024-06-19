package com.apress.BankingApi.Services;


import com.apress.BankingApi.Exception.ResourceNotFoundException;
import com.apress.BankingApi.Exceptions.CustomerNotFoundException;
import com.apress.BankingApi.Models.Deposit;
import com.apress.BankingApi.Repos.AccountRepository;
import com.apress.BankingApi.Repos.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

    @Service
    public class DepositService {

        @Autowired
        private DepositRepository depositRepository;
        @Autowired
        private AccountRepository accountRepository;

        // Verify
        public void verifyDepositById(Long id) {
            if (!depositRepository.findById(id).isPresent()) {
                throw new ResourceNotFoundException(String.valueOf(HttpStatus.NOT_FOUND));
            }
        }

        public void verifyAccountById(Long id) {
            if (!accountRepository.findById(id).isPresent()) {
                throw new ResourceNotFoundException(String.valueOf(HttpStatus.NOT_FOUND));
            }
        }

        // Create
        public Deposit createDeposit(Deposit deposit, Long accountId) {
            verifyAccountById(accountId);
            return depositRepository.save(deposit);
        }

        // Get
        public List<Deposit> getDepositsByAccountId(Long accountId) {
            verifyAccountById(accountId);
            return depositRepository.findDepositById(accountId);
        }

        // Get By Account ID
        public Deposit getDepositById(Long id) {
            verifyDepositById(id);
            return depositRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(HttpStatus.NOT_FOUND)));
        }

        // Update
        public Deposit updateDeposit(Deposit deposit) {
            verifyDepositById(deposit.getId());
            Deposit oldDeposit = getDepositById(deposit.getId());
            deposit.setId(oldDeposit.getId());
            deposit.setPayeeId(oldDeposit.getPayeeId());
            return depositRepository.save(deposit);
        }

        // Delete
        public void deleteDepositById(Long id) {
            verifyDepositById(id);
            depositRepository.deleteById(id);
        }
    }

