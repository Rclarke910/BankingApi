package com.apress.BankingApi.Models;

import com.apress.BankingApi.Enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Bill {
    @Id
    @GeneratedValue
    @Column(name = "BILL_ID")
    private Long id;
    private Status status;
    private String payee;
    private String nickname;
    private String creation_date;
    private String payment_date;
    private Integer recurring_date;
    private String upcoming_payment_date;
    private Double payment_amount;
    private String account_id;

    public long getId() {
        return id;
    }


}
