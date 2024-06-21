package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Account;
import com.apress.BankingApi.Models.P2P;
import org.springframework.data.jpa.repository.JpaRepository;

public interface P2PRepo extends JpaRepository<P2P, Long>
{

}
