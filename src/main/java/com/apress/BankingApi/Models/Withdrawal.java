package com.apress.BankingApi.Models;

import com.apress.BankingApi.Enums.AccountType;
import com.apress.BankingApi.Enums.Medium;
import com.apress.BankingApi.Enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Withdrawal {
    @Id
    @GeneratedValue
    @Column(name = "Withdrawal_ID")
    private Long id;
    @Column(name = "Withdrawal_Type")
    private AccountType type;
    @Column(name = "Withdrawal_Date")
    private String transaction_date;
    @Column(name = "Withdrawal_Status")
    private Status status;
    @Column(name = "Withdrawal_Payee")
    private Long payee_id;
    @Column(name = "Withdrawal_Medium")
    private Medium medium;
    @Column(name = "Withdrawal_amount")
    private Double amount;
    @Column(name = "Withdrawal_Description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
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

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
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
