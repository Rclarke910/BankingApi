package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
