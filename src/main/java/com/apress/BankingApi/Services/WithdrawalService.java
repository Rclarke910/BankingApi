package com.apress.BankingApi.Services;

import com.apress.BankingApi.Enums.TransactionType;
import com.apress.BankingApi.Models.Account;
import com.apress.BankingApi.Models.Customer;
import com.apress.BankingApi.Models.Deposit;
import com.apress.BankingApi.Models.Withdrawal;
import com.apress.BankingApi.Repos.AccountRepository;
import com.apress.BankingApi.Repos.WithdrawalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WithdrawalService {
   @Autowired
   private WithdrawalRepository withdrawalRepo;

   @Autowired
   private AccountRepository accountRepository;
//
//    public void verifyAccount(long id) {
//        Optional<Account> account = accountRepo.findById(id);
//        if(account.isEmpty()){
//            throw new EntityNotFoundException("Account not found");
//        }
//    }

    public Iterable<Withdrawal> getAllWithdrawals(){
            return withdrawalRepo.findAll();
    }
    public Iterable<Withdrawal> getAllAccountWithdrawals(long accountID)
    {
        return withdrawalRepo.findByAccountId(accountID);
    }
    public Withdrawal peerWithdrawalFromDeposit(long payerID, Deposit deposit)
    {
        Account payer = accountRepository.findById(payerID).orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + payerID));
        Withdrawal constructWithdrawal = new Withdrawal(TransactionType.P2P,payer,deposit.getMedium(), deposit.getAmount(), deposit.getDescription());
        return withdrawalRepo.save(constructWithdrawal);
    }
    public Optional<Withdrawal> getWithdrawalByID(Long withdrawalId){
        return withdrawalRepo.findById(withdrawalId);
    }
    public Withdrawal createWithdrawal(Withdrawal withdrawal, long accountId){
        Account payer = accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));
        withdrawal.setPayer(payer);
        return withdrawalRepo.save(withdrawal);
    }
    public Withdrawal updateWithdrawal(Long id, Withdrawal withdrawal){
        for(Withdrawal w : getAllWithdrawals()){
            if(w.getId().equals(id)){
                withdrawalRepo.save(withdrawal);
            }
        }
        return withdrawal;
    }
    public void deleteWithdrawalById(Long id){
        withdrawalRepo.deleteById(id);
    }


}
