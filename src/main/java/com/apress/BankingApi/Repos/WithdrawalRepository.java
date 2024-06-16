package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Withdrawal;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long> {
}
