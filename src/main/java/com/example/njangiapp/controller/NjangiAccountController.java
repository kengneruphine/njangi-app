package com.example.njangiapp.controller;


import java.util.ArrayList;
import java.util.Collection;

import com.example.njangiapp.model.Member;
import com.example.njangiapp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.njangiapp.repository.NjangiAccountRepository;
import com.example.njangiapp.model.NjangiAccount;

import java.util.List;
@RequestMapping("api/v1/account")
@RestController
public class NjangiAccountController {

    @Autowired
    private NjangiAccountRepository njangiAccountRepository;

    @Autowired
    private MemberRepository memberRepository;


    // get Njangi Account
    @RequestMapping(method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NjangiAccount>> getNjangiAccount(){
        List<NjangiAccount> njangiAccounts;
        njangiAccounts= njangiAccountRepository.findAll();
        return new ResponseEntity<>(njangiAccounts, HttpStatus.OK);
    }

    // get a particular Njangi account
    @RequestMapping(value="/{account_number}",
            method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public NjangiAccount getNjangiAccount1(@PathVariable("account_number") String account_number){

        NjangiAccount njangiAccount;
        njangiAccount = njangiAccountRepository.findByAccountNumber(account_number);
        return njangiAccount;
    }


    // create Njangi account
    @RequestMapping(method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NjangiAccount> createAccount(@RequestBody NjangiAccount njangiAccount){

        NjangiAccount njangiAccount1;
        njangiAccount1 = njangiAccountRepository.save(njangiAccount);

        return new ResponseEntity<>(njangiAccount1,HttpStatus.CREATED);

    }


        // Update Njangi account
    @RequestMapping(method=RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NjangiAccount> updateAccount(@RequestBody NjangiAccount njangiAccount){
        NjangiAccount njangiAccount1;
        njangiAccount1 = njangiAccountRepository.save(njangiAccount);
        return new ResponseEntity<>(njangiAccount1,HttpStatus.OK);
    }
}
