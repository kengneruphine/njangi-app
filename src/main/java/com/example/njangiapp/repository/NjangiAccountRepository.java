package com.example.njangiapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.njangiapp.model.NjangiAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NjangiAccountRepository extends JpaRepository<NjangiAccount, Integer> {

    NjangiAccount findByAccountNumber(String account_number);

    @Modifying()
    @Query(value = "update njangi_account  set amount= ?1 where id  = ?2 ", nativeQuery = true)
    @Transactional
    void updateAccount( double amount, Integer id);


}
