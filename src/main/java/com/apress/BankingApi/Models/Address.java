package com.apress.BankingApi.Models;


import jakarta.persistence.*;

@Entity
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private long id;
    @Column(name="ADDRESS_NUMBER")
    private String street_Number;
    @Column(name="STREET_NAME")
    private String street_Name;
    @Column(name="CITY")
    private String city;
    @Column(name="STATE")
    private String state;
    @Column(name="ZIP")
    private String zip;
}
