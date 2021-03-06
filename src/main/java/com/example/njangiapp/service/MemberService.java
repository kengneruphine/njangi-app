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


    public Boolean memberExists(final String username){
        return this.memberRepository.existsByUsername(username);
    }

    public Member findByUsername(final String username){
        return this.memberRepository.findByUsername(username);
    }

    public List<Member> fetchAllMembers(){
        return this.memberRepository.findAll();
    }

    public void delete(Member member){
        memberRepository.delete(member);
        return;
    }

    public Member save(Member member){
        return memberRepository.save(member);
    }
}