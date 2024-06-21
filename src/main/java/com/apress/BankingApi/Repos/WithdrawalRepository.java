package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Bill;
import com.apress.BankingApi.Models.Withdrawal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long>
{
    @Query(value="select w.* from Account a, Withdrawal w where a.ID = ?1 and w.ACCOUNT_ID = a.ID", nativeQuery = true)
        // @Query(value = "SELECT b FROM Bill b WHERE b.account_id = :accountId",nativeQuery = true)
    List<Withdrawal> findByAccountId(Long accountId);
}
