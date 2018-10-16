package com.example.njangiapp.service.implementation;

import com.example.njangiapp.model.Transaction;
import com.example.njangiapp.repository.TransactionRepository;
import com.example.njangiapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;


@Service
//@Secured("ROLE_ADMINISTRATION")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Collection<Transaction> findAll() {

        Collection<Transaction> transactions = transactionRepository.findAll();
        return transactions;
    }

    @Override
    public Transaction findById(int id) {

        Transaction transaction = transactionRepository.findById(id);
        return transaction;
    }

    @Override
    public  Collection<Transaction> findByMemberIdentifier(String identifier){
        Collection<Transaction> transaction = transactionRepository.findByMemberIdentifier(identifier);
        return transaction;
    }

    @Override
    public  Collection<Transaction> findByDate(Date date){
        Collection<Transaction> transaction = transactionRepository.findByDate(date);
        return transaction;
    }

    @Override
    public Collection<Transaction> findByType(String type) {

       Collection<Transaction> transaction = transactionRepository.findByType(type);
        return transaction;
    }

    @Override
    public Transaction create(Transaction transaction) {

        Transaction savedTransaction = transactionRepository.save(transaction);
        return savedTransaction;
    }

    @Override
    public Transaction update(Transaction transaction) {

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return updatedTransaction;
    }
}
