package com.apress.BankingApi.Models;

import com.apress.BankingApi.Enums.Medium;
import com.apress.BankingApi.Enums.Status;
import com.apress.BankingApi.Enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEPOSIT_ID")
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @CreationTimestamp
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name="ACCOUNT_ID")
    private Account payee;
    private Medium medium;
    private Double amount;
    private String description;

    public Deposit() {
    }
    public Deposit(TransactionType type,
                   Account payee, Medium medium, Double amount, String description) {
        this.type = type;
        this.status = Status.Pending;
        this.payee = payee;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
    }
    public Deposit(TransactionType type,
                   Account payee, Medium medium, Double amount)
    {
        this.type = type;
        this.status = Status.Pending;
        this.payee = payee;
        this.medium = medium;
        this.amount = amount;
        this.description = "P2P transfer";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Account getPayee() {
        return payee;
    }

    public void setPayee(Account payee) {
        this.payee = payee;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
