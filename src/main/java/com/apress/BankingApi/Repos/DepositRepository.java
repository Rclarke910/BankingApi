package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {
    List<Deposit> findByPayeeId(Long accountId);
}
