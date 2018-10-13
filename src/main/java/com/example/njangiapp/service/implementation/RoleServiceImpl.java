package com.example.njangiapp.service.implementation;

import com.example.njangiapp.model.Role;
import com.example.njangiapp.repository.RoleRepository;
import com.example.njangiapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Secured("ROLE_ADMINISTRATION")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Collection<Role> findAll() {

        Collection<Role> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public Role findById(int id) {

        Role role = roleRepository.findById(id);
        return role;
    }

    @Override
    public Role findByName(String name) {

        Role role = roleRepository.findByName(name);
        return role;
    }

    @Override
    public Role create(Role role) {

        Role savedRole = roleRepository.save(role);
        return savedRole;
    }

    @Override
    public Role update(Role role) {

        Role updatedRole = roleRepository.save(role);
        return updatedRole;
    }

}
