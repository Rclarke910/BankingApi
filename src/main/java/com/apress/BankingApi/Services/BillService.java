package com.apress.BankingApi.Services;

import com.apress.BankingApi.Models.Bill;
import com.apress.BankingApi.Repos.BillRepository;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    public void savePoll(Bill bill)
    {
        billRepository.save(bill);
    }
    public Iterable<Bill> getAllBills (){
        return billRepository.findAll();
    }

    public void deleteBillById(Long id) {
        billRepository.deleteById(id);
    }

    public Optional<Bill> getPollById(Long id){
        return billRepository.findById(id);
    }

    @PostUpdate
    public void updatePoll(Bill bill, Long id){
        for(Bill b : getAllBills()) {
            if (b.getId() == (id)) {
                billRepository.save(bill);
            }
        }
    }
}
