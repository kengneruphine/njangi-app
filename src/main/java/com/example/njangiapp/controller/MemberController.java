package com.example.njangiapp.controller;

import java.util.ArrayList;
import java.util.Collection;

import com.example.njangiapp.model.NjangiAccount;
import com.example.njangiapp.repository.MemberRepository;
import com.example.njangiapp.repository.NjangiAccountRepository;
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

import com.example.njangiapp.model.Member;
import com.example.njangiapp.service.MemberService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("api/v1/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private NjangiAccountRepository njangiAccountRepository;

    /**
     * Get all members or collection of active member or member with a given username.
     *
     * @param username, isActive
     * @return Collection of members or member with the particular username
     */
    @RequestMapping(method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Member>> getMembers(@RequestParam(value = "username", required = false) String username,
                                                             @RequestParam(value = "active", required = false) String isActive){

        Collection<Member> members = new ArrayList<>();
        if (username != null) {
            Member member = memberRepository.findByUsername(username);
            members.add(member);

        } else if (isActive != null) {
            if (isActive.compareToIgnoreCase("true") == 0){
                Collection<Member> activeMembers = memberRepository.findByIsActive(true);
                members.addAll(activeMembers);

            } else if(isActive.compareToIgnoreCase("false") == 0) {
                Collection<Member> deactivatedMembers = memberRepository.findByIsActive(false);
                members.addAll(deactivatedMembers);

            }
        } else {
            Collection<Member> allMembers = memberRepository.findAll();
            members.addAll(allMembers);
        }

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * Get member with given customer id.
     *
     * @param memberId
     * @return member object or 404 if member is not found
     */
    @RequestMapping(value="/{memberId}",
            method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getMemberById(@PathVariable("memberId") int memberId){

        Member member = memberService.findById(memberId);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * Create new member.
     *
     * @param member
     * @return member object (created object)
     */
    @RequestMapping(method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createMember(@RequestBody Member member){

        member = memberService.create(member);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    /**
     * Update member.
     *
     * @param member
     * @return member object (updated object)
     */
    @RequestMapping(value="/{memberId}",
            method=RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@RequestBody Member member){

        member = memberService.update(member);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * Deactivate a Member's account
     *
     * @param memberId
     * @return HTTP status, NON_CONTENT
     */
    @RequestMapping(value="/{memberId}",
            method=RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> deActivateMember(@PathVariable("memberId") int memberId){

        memberService.deactivate(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/members/{id}/withdraw/{account_balance}/{njangi_account_number}", method = RequestMethod.PUT )
        public void withdrawAmount(@PathVariable("id") int id , @PathVariable("account_balance") double account_balance, @PathVariable("njangi_account_number") String njangi_account_number){

        if(!this.memberRepository.existsById(id)){

            double amount = this.memberRepository.findById(id).getAccountBalance();
            if(amount >= account_balance) {
                this.memberRepository.updateMemberAccount((amount - account_balance), id);

              NjangiAccount njangiAccount = this.njangiAccountRepository.findByAccountNumber(njangi_account_number);
              double njangiAccountBalance = njangiAccount.getAmount();
                this.njangiAccountRepository.updateAccount((njangiAccountBalance + account_balance), njangiAccount.getId());
            }

        }

    }



}
