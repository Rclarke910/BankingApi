package com.apress.BankingApi.Services;


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


        public void saveDeposit(Deposit deposit) {
            depositRepository.save(deposit);
        }

        public void deleteDeposit(Deposit deposit) {
            depositRepository.delete(deposit);
        }

        public Iterable<Deposit> getAllDeposits() {
            return depositRepository.findAll();
        }

        public Optional<Deposit> getDepositById(Long id) {
            return depositRepository.findById(id);
        }

        public void updateDeposit(Long id, Deposit deposit) {
            for (Deposit d : getAllDeposits()) {
                if (d.getId().equals(id)) {
                    depositRepository.save(deposit);
                }
            }
        }
    }




