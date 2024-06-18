package com.apress.BankingApi.Models;

import com.apress.BankingApi.Enums.AccountType;
import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private String nickname;
    private Integer rewards;
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}