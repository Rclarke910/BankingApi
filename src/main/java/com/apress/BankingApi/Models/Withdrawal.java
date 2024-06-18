package com.apress.BankingApi.Models;

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
    private String type;
    @Column(name = "Withdrawal_Date")
    private String transaction_date;
    @Column(name = "Withdrawal_Status")
    private String status;
    @Column(name = "Withdrawal_Payee")
    private Long payee_id;
    @Column(name = "Withdrawal_Medium")
    private String medium;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
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
