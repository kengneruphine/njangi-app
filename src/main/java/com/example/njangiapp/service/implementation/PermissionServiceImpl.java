package com.example.njangiapp.service.implementation;

import com.example.njangiapp.model.Permission;
import com.example.njangiapp.repository.PermissionRepository;
import com.example.njangiapp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Secured("ROLE_ADMINISTRATION")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Collection<Permission> findAll() {

        Collection<Permission> permissions = permissionRepository.findAll();
        return permissions;
    }

    @Override
    public Permission findById(int id) {

        Permission permission = permissionRepository.findById(id);
        return permission;
    }

    @Override
    public Permission findByName(String name) {

        Permission permission = permissionRepository.findByName(name);
        return permission;
    }

    @Override
    public Permission create(Permission permission) {

        Permission savedPermission = permissionRepository.save(permission);
        return savedPermission;
    }

    @Override
    public Permission update(Permission permission) {

        Permission updatedPermission = permissionRepository.save(permission);
        return updatedPermission;
    }
}

