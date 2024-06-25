package com.apress.BankingApi.Models;

import com.apress.BankingApi.Enums.Medium;
import com.apress.BankingApi.Enums.Status;
import com.apress.BankingApi.Enums.TransactionType;
import jakarta.persistence.*;

@Entity
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String transactionDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Medium medium;

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
    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
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
}
