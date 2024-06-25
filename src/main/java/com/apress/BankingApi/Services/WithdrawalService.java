package com.apress.BankingApi.Services;

import com.apress.BankingApi.Models.Account;
import com.apress.BankingApi.Models.Customer;
import com.apress.BankingApi.Models.Withdrawal;
import com.apress.BankingApi.Repos.AccountRepository;
import com.apress.BankingApi.Repos.WithdrawalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WithdrawalService {
   @Autowired
   private WithdrawalRepository withdrawalRepo;

   @Autowired
   private AccountRepository accountRepository;


    public Iterable<Withdrawal> getAllWithdrawals(){
            return withdrawalRepo.findAll();
    }

    public Iterable<Withdrawal> getAllAccountWithdrawals(long accountID)
    {
        return withdrawalRepo.findByPayerId(accountID);
    }

    public Optional<Withdrawal> getWithdrawalByID(Long withdrawalId){
        return withdrawalRepo.findById(withdrawalId);
    }


    public Withdrawal createWithdrawal(Withdrawal withdrawal, long accountId){
        Account payer = accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));
        withdrawal.setPayer(payer);
        return withdrawalRepo.save(withdrawal);
    }


    public Withdrawal updateWithdrawal(Long id, Withdrawal withdrawal) {
        Withdrawal existingWithdrawal = withdrawalRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Withdrawal not found with id: " + id));
        existingWithdrawal.setAmount(withdrawal.getAmount());
        existingWithdrawal.setDescription(withdrawal.getDescription());
        existingWithdrawal.setMedium(withdrawal.getMedium());
        existingWithdrawal.setStatus(withdrawal.getStatus());
        existingWithdrawal.setTransactionDate(withdrawal.getTransactionDate());
        existingWithdrawal.setType(withdrawal.getType());
        return withdrawalRepo.save(existingWithdrawal);
    }


    public void deleteWithdrawalById(Long id){
        withdrawalRepo.deleteById(id);
    }


}
