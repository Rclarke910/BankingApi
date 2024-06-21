package com.apress.BankingApi.Services;

import com.apress.BankingApi.Exception.CustomerNotFoundException;
import com.apress.BankingApi.Models.Bill;
import com.apress.BankingApi.Repos.BillRepository;
import com.apress.BankingApi.Repos.CustomerRepository;
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
    public void saveBill(Bill bill)
    {
        billRepository.save(bill);
    }
    public List<Bill> getBillsByAccountId(Long accountId) {
        return billRepository.findByAccountId(accountId);
    }
    public Optional<Bill> getAllCustomerBills(Long customer_Id) throws Exception {
        billRepository.findById(customer_Id);
        return billRepository.findById(customer_Id);
    }
    public Bill createBill(Bill bill) throws CustomerNotFoundException {

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
