package com.example.njangiapp.service;

import com.example.njangiapp.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public interface TransactionService {

    /**
     * Get all transactions in the system.
     *
     * @return Collection of all transactions in the system
     */
    public Collection<Transaction> findAll();

    /**
     * Find a transaction by id.
     *
     * @param
     * @return Transaction object if found else null
     */
    public Transaction findById(int transactionId);

    /**
     * Find transactions made by member with id, memberId.
     *
     * @param
     * @return Collection of Transaction objects
     */
    public Collection<Transaction> findByMemberUsername(String username);

    /**
     * Find transactions with due date,
     *
     * @param
     * @return Collection of Transaction objects
     */
    public Collection<Transaction> findByDate(Date date);

    public Collection<Transaction> findByType(String type);


    public Transaction create(Transaction transaction);

    /**
     * Update an existing transaction's information.
     *
     * @param transaction
     * @return Transaction object (updated Transaction object)
     */
    public Transaction update(Transaction transaction);
}

