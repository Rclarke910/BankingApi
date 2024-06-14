package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Withdrawl;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawlRepository extends CrudRepository<Withdrawl, Long> {
}
