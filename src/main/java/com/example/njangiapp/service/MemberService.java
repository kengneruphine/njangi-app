package com.example.njangiapp.service;


import com.example.njangiapp.model.Member;


import com.example.njangiapp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(final MemberRepository memberRepository){
        super();
        this.memberRepository = memberRepository;
    }


    public Boolean memberExists(final String identifier){
        return this.memberRepository.existsByIdentifier(identifier);
    }

    public Member findByIdentifier(final String identifier){
        return this.memberRepository.findByIdentifier(identifier);
    }

    public List<Member> fetchAllMembers(){
        return this.memberRepository.findAll();
    }
}