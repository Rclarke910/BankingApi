package com.apress.BankingApi.Models;

import com.apress.BankingApi.Enums.Medium;
import com.apress.BankingApi.Enums.Status;
import com.apress.BankingApi.Enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="WITHDRAWAL_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @CreationTimestamp
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Medium medium;
    @NotNull
    private Double amount;
    private String description;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account payer;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
    public Account getPayer() {
        return payer;
    }

    public void setPayer(Account payer) {
        this.payer = payer;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Withdrawal(TransactionType type, Account payer,
                      Medium medium, Double amount, String description) {
        this.type = type;
        this.status = Status.Pending;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
        this.payer = payer;
    }
    public Withdrawal()
    {
    }
}
