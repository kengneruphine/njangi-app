package com.example.njangiapp.service;


import com.example.njangiapp.model.Member;


import java.util.Collection;

/**
 *  Service that provides CRUD operations for customers
 **/
public interface MemberService {

    /**
     * Get all members in the system.
     *
     * @return Collection of all customers
     */
    public Collection<Member> findAll();

    /**
     * Find a customer by Id.
     *
     * @param id
     * @return member object if found, else return null
     */
    public Member findById(int id);

    /**
     * Find a member by username.
     *
     * @param username
     * @return member object if found, else return null
     */
    public Member findByUsername(String username);

    /**
     * Find a members with active/ deactivated accounts.
     *
     * @param isActive
     * @return Collection of members
     */
    public Collection<Member> findByIsActive(boolean isActive);

    /**
     * Create a new member account.
     *
     * @param member
     * @return member bject (Created member object)
     */
    public Member create(Member member);

    /**
     * Update an existing member account's information.
     *
     * @param member
     * @return member object (Updated member object)
     */
    public Member update(Member member);

    /**
     * Deactivate a member account from the system.
     *
     * @param id
     */
    public void deactivate(int id);

}

