package com.example.njangiapp.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name= "identifier", nullable = false)
    private String identifier;
    @Column( name ="first_name", nullable = false)
    private String firstName;
    @Column( name ="last_name", nullable= false)
    private String lastName;
    @Column( name ="username" , nullable = false)
    private String username;
    @Column (name ="email", nullable = false)
    private String email;
    @Column(name ="password", nullable = false)
    private String password;
    @Column(name="phone_number", nullable = false)
    private String phoneNumber;
    @Column (name ="location", nullable = false)
    private String location;
    @Column(name="occupation" , nullable = false)
    private String occupation;
    @Column(name="picture", nullable = false)
    private String picture;
    @Column(name="account_balance", nullable = false)
    private Double accountBalance;
    @Column(name="date_of_birth", nullable = false)
    private Date dateOfBirth;
    @Column(nullable=false)
    private boolean isActive;
    @Column(name="account_number",nullable = false)
    private String accountNumber;



    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
    private Set<Transaction> transaction;

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }



    public Member(){
        super();

    }

    public Member(String identifier, String firstName, String lastName, String username, String email, String password, String phoneNumber, String location,
                  String occupation, String picture, Double accountBalance, Date dateOfBirth,Boolean isActive, String accountNumber) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.occupation = occupation;
        this.picture = picture;
        this.accountBalance = accountBalance;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
        this.accountNumber = accountNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
