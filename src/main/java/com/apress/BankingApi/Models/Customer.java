package com.apress.BankingApi.Models;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    @OrderBy
    private Long id;

    @NotBlank(message = "The first name cannot be empty.")
    @Column(name = "first_name")
    private String firstName;


    @NotBlank(message = "The last name cannot be empty.")
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy
    private Set<Address> addresses;


    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, Set<Address> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }


    public Customer(String firstName, String lastName, Set<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

}