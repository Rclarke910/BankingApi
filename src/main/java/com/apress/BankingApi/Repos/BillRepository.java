package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query(value="select b.* from Account a, Bill b where a.ID = ?1 and b.ACCOUNT_ID = a.ID", nativeQuery = true)
   // @Query(value = "SELECT b FROM Bill b WHERE b.account_id = :accountId",nativeQuery = true)
    List<Bill> findByAccountId( Long accountId);
//    @Query(value="select b.* from Account a, Bill b where a.CUSTOMER_ID = ?1 and b.ACCOUNT_ID = a.ACCOUNT_ID", nativeQuery = true)
//    List<Bill> findByCustomerId (Long customerId) ;
}


