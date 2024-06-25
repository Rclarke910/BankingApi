package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(Long customerId);
}
