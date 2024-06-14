package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {

}
