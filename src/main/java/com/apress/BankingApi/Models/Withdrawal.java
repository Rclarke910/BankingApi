package com.apress.BankingApi.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Withdrawl {
    @Id
    @GeneratedValue
    @Column(name = "Withdrawl_ID")
    private Long id;
    @Column(name = "Withdrawl_ID")
    private String type;
    @Column(name = "Withdrawl_ID")
    private String transaction_date;
    @Column(name = "Withdrawl_ID")
    private String status;
    @Column(name = "Withdrawl_ID")
    private Long payee_id;
    @Column(name = "Withdrawl_ID")
    private String medium;
    @Column(name = "Withdrawl_ID")
    private Double amount;
    @Column(name = "Withdrawl_ID")
    private String description;
    @Column(name = "Withdrawl_ID")

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
