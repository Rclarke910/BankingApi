package com.apress.BankingApi.Models;

import com.apress.BankingApi.Enums.Medium;
import com.apress.BankingApi.Enums.Status;
import com.apress.BankingApi.Enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.Optional;

@Entity
public class P2P
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="WITHDRAWAL_ID")
    private Withdrawal payment;
    @OneToOne
    @JoinColumn(name="DEPOSIT_ID")
    private Deposit deposit;
    public P2P()
    {}
    public P2P(Withdrawal createdWithdrawal, Deposit createdDeposit)
    {
        this.payment=createdWithdrawal;
        this.deposit=createdDeposit;
    }

    public Long getId() {
        return id;
    }

    public Withdrawal getPayment() {
        return payment;
    }

    public void setPayment(Withdrawal payment) {
        this.payment = payment;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }
    public static Deposit peerDepositConstructor(Deposit deposit)
    {
        @Valid Deposit constructedDeposit = new Deposit(TransactionType.P2P,deposit.getPayee(),deposit.getMedium(),deposit.getAmount(), deposit.getDescription());
        return constructedDeposit;
    }

}
