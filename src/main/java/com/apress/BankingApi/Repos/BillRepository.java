package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long>
{
    @Query(value="select b.* from Account a, Bill b where a.ACCOUNT_ID = ?1 and b.ACCOUNT_ID = a.ACCOUNT_ID", nativeQuery = true)
    public Iterable<Bill> findByAccount(Long pollId);
}
