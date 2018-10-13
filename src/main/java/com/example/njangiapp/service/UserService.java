package com.example.njangiapp.service;


import com.example.njangiapp.model.UserSys;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {

    /**
     * Get all users in the system.
     *
     * @return Collection of all users
     */
    public Collection<UserSys> findAll();

    /**
     * Find an user by Id.
     *
     * @param id
     * @return CustomUser object if found, else return null
     */
    public UserSys findById(int id);

    /**
     * Find an user by username.
     *
     * @param username
     * @return CustomUser object if found, else return null
     */
    public UserSys findByUserName(String username);

    /**
     * Find users with active accounts.
     *
     * @param isActive
     * @return Collection of users
     */
    public Collection<UserSys> findByIsActive(boolean isActive);

    /**
     * Create new userSys.
     *
     * @param userSys
     * @return CustomUser object (Created userSys object)
     */
    public UserSys create(UserSys userSys);

    /**
     * Update an existing userSys's information.
     *
     * @param userSys
     * @return CustomUser object (Updated userSys object)
     */
    public UserSys update(UserSys userSys);

    /**
     * Deactivate a user's account.
     *
     * @param id
     */
    public void deactivate(int id);


}
