package com.apress.BankingApi.Services;

import com.apress.BankingApi.Exception.ResourceNotFoundException;
import com.apress.BankingApi.Models.Account;
import com.apress.BankingApi.Models.Customer;
import com.apress.BankingApi.Repos.AccountRepository;
import com.apress.BankingApi.Repos.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    protected void verifyAccount(long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account == null){
            throw new EntityNotFoundException("Account not found");
        }
    }

    public Account createAccount(Account account, long customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(long id){return accountRepository.findById(id);}

    public Iterable<Account> getAllAccounts(){return accountRepository.findAll();}

    public Optional<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public Account updateAccount(long id,Account account){
        verifyAccount(id);
       return accountRepository.save(account);
    }

    public void deleteAccount(long id) {
        if (!accountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Account with id '" + id + "' is not found");
        }
        accountRepository.deleteById(id);
    }
}
