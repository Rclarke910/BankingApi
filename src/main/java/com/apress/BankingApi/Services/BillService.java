package com.apress.BankingApi.Services;

import com.apress.BankingApi.Exception.CustomerNotFoundException;
import com.apress.BankingApi.Models.Account;
import com.apress.BankingApi.Models.Bill;
import com.apress.BankingApi.Repos.AccountRepository;
import com.apress.BankingApi.Repos.BillRepository;
import com.apress.BankingApi.Repos.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    public void saveBill(Bill bill)
    {
        billRepository.save(bill);
    }
    public List<Bill> getBillsByAccountId(Long accountId) {
        return billRepository.findByAccountId(accountId);
    }
    public List <Bill> getAllCustomerBills(Long customer_Id) throws Exception {
        billRepository.findById(customer_Id);
        return billRepository.findByCustomerId(customer_Id);
    }
    public Bill createBill(Bill bill, Long accountId) throws CustomerNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + accountId));
       bill.setAccount(account);

       // logger.info("successfully created Customer");
        return billRepository.save(bill);

    }

    public void deleteBillById(Long id) {
        billRepository.deleteById(id);
    }

    public Optional<Bill> getBillById(Long id){
        return billRepository.findById(id);
    }

    @PostUpdate
    public Bill updateBill(Bill bill, Long id){
        for(Bill b : getBillsByAccountId(id)) {
            if (b.getId().equals(id)) {
                billRepository.save(bill);
            }
        }
        return bill;
    }
}
