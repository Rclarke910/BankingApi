package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query(value = "SELECT b FROM Bill b WHERE b.account_id = :accountId",nativeQuery = true)
    List<Bill> findByAccountId(@Param("accountId") Long accountId);

}


