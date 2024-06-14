package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
