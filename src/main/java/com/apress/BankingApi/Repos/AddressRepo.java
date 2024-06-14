package com.apress.BankingApi.Repos;

import com.apress.BankingApi.Models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Long>
{}