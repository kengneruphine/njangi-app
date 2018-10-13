package com.example.njangiapp.repository;

import com.example.njangiapp.model.UserSys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserSys, Integer> {

    UserSys findByUsername(@Param("name") String name);
    Collection<UserSys> findByIsActive(@Param("isActive") boolean isActive);

    UserSys findById(@Param("id") int id);

}
