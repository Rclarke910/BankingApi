package com.apress.BankingApi.Services;

import com.apress.BankingApi.Models.Account;
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
   private AccountRepository accountRepo;

    public void verifyAccount(long id) {
        Optional<Account> account = accountRepo.findById(id);
        if(account.isEmpty()){
            throw new EntityNotFoundException("Account not found");
        }
    }

    public Iterable<Withdrawal> getAllWithdrawals(){
            return withdrawalRepo.findAll();
    }

    public Optional<Withdrawal> getWithdrawalByID(Long withdrawalId){
        return withdrawalRepo.findById(withdrawalId);
    }
    public void createWithdrawal(Withdrawal withdrawal){
        withdrawalRepo.save(withdrawal);
    }
    public void updateWithdrawal(Long id, Withdrawal withdrawal){
        for(Withdrawal w : getAllWithdrawals()){
            if(w.getId().equals(id)){
                withdrawalRepo.save(withdrawal);
            }
        }
    }
    public void deleteWithdrawalById(Long id){
        withdrawalRepo.deleteById(id);
    }


}
