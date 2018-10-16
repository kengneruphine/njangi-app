package com.example.njangiapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    private MemberRepository memberRepository;

    @Autowired
    private NjangiAccountRepository njangiAccountRepository;

    //get all members
  /*  @RequestMapping(method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> getMembers(){

       List<Member> members;
       members = memberRepository.findAll();
       return new ResponseEntity<>(members,HttpStatus.OK);
    }
*/

    @RequestMapping(method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Member>> getMembers(@RequestParam(value = "username" ,required = false) String username,
                                                         @RequestParam(value = "active",required = false) String isActive){

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


    //get a particular member
    @RequestMapping(value="/{identifier}",
            method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getMemberByIdentifier(@PathVariable("identifier") String identifier){

        Member member = memberRepository.findByIdentifier(identifier);
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

        member = memberRepository.save(member);
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
    @RequestMapping(value="/{identifier}",
            method=RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@PathVariable("identifier") String identifier, @RequestBody Member member){

        Member member1 = memberRepository.findByIdentifier(identifier);

        member1 = memberRepository.save(member);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(member1, HttpStatus.OK);
    }

    /**
     * Deactivate a Member's account
     *
     * @param memberId
     * @return HTTP status, NON_CONTENT
     */
    /*@RequestMapping(value="/{memberId}",
            method=RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> deActivateMember(@PathVariable("memberId") int memberId){

     memberRepository.
        memberService.deactivate(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    }
*/


}
