package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepositRepository extends CrudRepository<Deposit, Long> {
    List<Deposit> findDepositById(Long accountId);
}
