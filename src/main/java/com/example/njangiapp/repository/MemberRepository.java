package com.example.njangiapp.repository;

import com.example.njangiapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

   // @Query(value= "SELECT * FROM member WHERE username = ?1", nativeQuery = true)
   // Optional<Member> findMember(String username);

    Member findByUsername(String username);
    Collection<Member> findByIsActive(@Param("isActive") boolean isActive);

    Member findById(@Param("id") int id);

    @Modifying()
    @Query(value = "update member  set account_balance= ?1 where id  = ?2 ", nativeQuery = true)
    @Transactional
    void updateMemberAccount(double account_balance,Integer id);

    Member findByAccountNumber(String account_number);
}
