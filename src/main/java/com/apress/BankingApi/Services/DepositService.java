package com.apress.BankingApi.Services;

import com.apress.BankingApi.Exception.ResourceNotFoundException;
import com.apress.BankingApi.Models.Deposit;
import com.apress.BankingApi.Repos.AccountRepository;
import com.apress.BankingApi.Repos.DepositRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
    public class DepositService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private AccountRepository accountRepository;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(DepositService.class);

    // Verify
    public void verifyDepositById(Long id) throws ResourceNotFoundException {
        if (!depositRepository.findById(id).isPresent()) {
            logger.error("Deposit not found");
            throw new ResourceNotFoundException("Deposit with id '" + id + "' is not found");
        }
    }
    public void verifyAccountById(Long id) throws ResourceNotFoundException {
        if (!accountRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Account with id '" + id + "' is not found");
        }
    }
    // Create
    public Deposit createDeposit(Deposit deposit, Long accountId) {
        deposit.setPayeeId(accountId);
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
                .orElseThrow(() -> new ResourceNotFoundException("Deposit with id '" + id + "' is not found"));
    }
    // Update
    public Deposit updateDeposit(Deposit deposit, Long depositId) {
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
    // Get All
    public List<Deposit> getAllDeposits() {
        return (List<Deposit>) depositRepository.findAll();
    }
}


