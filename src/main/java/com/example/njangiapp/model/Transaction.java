package com.example.njangiapp.model;

import javax.persistence.*;

@Entity
@Table(name ="transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name ="type", nullable = false)
    private String type;
    @Column(name ="amount", nullable = false)
    private Double amount;
    @Column(name ="date", nullable = false)
    private String date;
    @Column(name ="note", nullable = false)
    private String note;


    @ManyToOne
    @JoinColumn(name ="member_id")
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


    @ManyToOne
    @JoinColumn(name ="njangi_account_id")
    private NjangiAccount njangiAccount;
    public NjangiAccount getNjangiAccount() {
        return njangiAccount;
    }

    public void setNjangiAccount(NjangiAccount njangiAccount) {
        this.njangiAccount = njangiAccount;
    }

    public Transaction(){super();}

    public Transaction(String type, Double amount, String date, String note, Member member, NjangiAccount njangiAccount) {
        super();
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.member = member;
        this.njangiAccount = njangiAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public double depositAmount(Member member, NjangiAccount njangiAccount){

        double depositAmount = 5000.0;
        if(member.getAccountBalance() <6000.0){
            System.out.println("You dont have enough money in your account");
            return 0.0;
        }

        double balance = member.getAccountBalance() - depositAmount;

        // add the deposit amount to what is in the njangi account
        double njangiAmount = njangiAccount.getAmount() + depositAmount;

         njangiAccount.setAmount(njangiAmount);

         member.setAccountBalance(balance);

        return depositAmount;

    }

    public double withdrawAmount(Member member, NjangiAccount njangiAccount){
        // any withdrawal must leave 2000 in the njangi account
         double amountLeft = 2000.0;

        double amount = njangiAccount.getAmount() - amountLeft;

        //add withdraw amount to the member account

        double withdrawAmount = member.getAccountBalance() + amount;

        member.setAccountBalance(withdrawAmount);

        njangiAccount.setAmount(amountLeft);

        return amount;
    }
}
