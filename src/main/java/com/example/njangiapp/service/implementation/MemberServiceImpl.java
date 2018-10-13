package com.example.njangiapp.service.implementation;


import com.example.njangiapp.model.Member;
import com.example.njangiapp.repository.MemberRepository;
import com.example.njangiapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Secured("ROLE_ADMINISTRATION")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Collection<Member> findAll() {

        Collection<Member> members =  memberRepository.findAll();
        return members;
    }

    @Override
    public Member findById(int id) {


        Member member =  memberRepository.findById(id);
        return member;
    }

    @Override
    public Member findByUsername(String username) {

        Member  member=  memberRepository.findByUsername(username);
        return  member;
    }

    @Override
    public Collection<Member> findByIsActive(boolean isActive) {

        Collection<Member> members = memberRepository.findByIsActive(isActive);
        return members;
    }

    @Override
    public Member create(Member member) {

        if (findByUsername(member.getUsername()) == null) {
            Member savedMember =  memberRepository.save(member);
            return savedMember;
        }

        return null;
    }

    @Override
    public Member update(Member member) {

        Member savedMember =  memberRepository.save(member);
        return savedMember;

    }

    @Override
    public void deactivate(int id) {

        Member member = findById(id);
        if (member == null){
            return;
        }

        member.setActive(false);
    }

}

