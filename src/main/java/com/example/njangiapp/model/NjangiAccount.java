package com.example.njangiapp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "njangi_account")
public class NjangiAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="account_number",nullable = false)
    private String accountNumber;
    @Column(name ="amount", nullable = false)
    private Double amount;



    @OneToMany(mappedBy = "njangiAccount", cascade = CascadeType.ALL)
    private Set<Transaction> transaction;

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }


    public NjangiAccount(){super();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
