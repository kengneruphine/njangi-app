package com.example.njangiapp.repository;

import com.example.njangiapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

  //  @Query(value = "SELECT * FROM transaction WHERE type = ?1" , nativeQuery = true)

    Collection<Transaction> findByType(@Param("type") String type );
    Collection<Transaction> findByMemberIdentifier(@Param("identifier") String identifier );
    Collection<Transaction> findByDate(@Param("date") Date date);

    Transaction findById(int id);
}
