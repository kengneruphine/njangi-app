package com.example.njangiapp.repository;

import com.example.njangiapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(@Param("name") String name);

    Role findById(@Param("id") int id);

}
