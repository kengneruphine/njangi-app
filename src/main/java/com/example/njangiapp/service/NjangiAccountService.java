package com.example.njangiapp.service;

import com.example.njangiapp.model.NjangiAccount;

import java.util.Collection;

public interface NjangiAccountService {

    public NjangiAccount create(NjangiAccount njangiAccount);

    /**
     * Update an existing customer account's information.
     *
     * @param njangi
     * @return Customer object (Updated njangi object)
     */
    public NjangiAccount update(NjangiAccount njangiAccount);


    public Collection<NjangiAccount> findAll();

    /**
     * Find a customer by Id.
     *
     * @param id
     * @return Customer object if found, else return null
     */
    public NjangiAccount findById(int id);

}
