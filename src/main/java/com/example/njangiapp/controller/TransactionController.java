package com.example.njangiapp.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

import com.example.njangiapp.model.Transaction;
import com.example.njangiapp.repository.TransactionRepository;
import com.example.njangiapp.service.TransactionService;
import com.example.njangiapp.model.Member;
import com.example.njangiapp.model.NjangiAccount;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    @Autowired
    private MemberRepository memberRepository;
    private TransactionRepository transactionRepository;

    private Member member;
    private NjangiAccount njangiAccount;

    @Autowired
    private NjangiAccountRepository njangiAccountRepository;

    @Autowired
    private TransactionService transactionService;

    /**
     * Get all transactions or a member transaction  with a given id.
     *
     * @param type, memberId,date
     * @return Collection of transactions or a member's transactions with the given id.
     * @throws ParseException
     */

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Transaction>> getTransactions(@RequestParam(value = "date", required = false) String date,
                                                                  // @RequestParam(value = "memberId", required = false) int memberId,
                                                                   @RequestParam(value = "type", required = false) String type)
            throws ParseException {

        Collection<Transaction> transactions = new ArrayList<>();

        if (date != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = formatter.parse(date);
            Collection<Transaction> transaction = transactionService.findByDate(date1);
            transactions.addAll(transaction);

        }
        /*else if (memberId != -1) {
            Collection<Transaction> transaction = transactionService.findByMemberId(memberId);
            transactions.addAll(transaction);

        }*/
        else if (type != null){
            Collection<Transaction> transaction = transactionService.findByType(type);
            transactions.addAll(transaction);
        }

        else {
            Collection<Transaction> allTransactions = transactionService.findAll();
            transactions.addAll(allTransactions);
        }

        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }

    //get transactions of a single member
    @RequestMapping(value="/{username}",
            method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Transaction>> getMemberTransaction(@PathVariable("username") String username){

        Collection<Transaction> transaction = transactionService.findByMemberUsername(username);
        if (transaction == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }



    /**
     * Get transaction with given transaction id.
     *
     * @param transactionId
     * @return Transaction object or 404 if transaction is not found
     */
    @RequestMapping(value="/{transactionId}",
            method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("transactionId") int transactionId){

        Transaction transaction = transactionService.findById(transactionId);
        if (transaction == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    /**
     * Create new transaction record.
     *
     * @param transaction
     * @return Transaction object (created Transaction object)
     */

    //create transaction with a member username

    @RequestMapping(value="{username}", method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction ,@PathVariable("username")String username){

        String type = transaction.getType();

         List<NjangiAccount> njangiAccount = this.njangiAccountRepository.findAll();


        double njangiAmount = this.njangiAccount.getAmount();
        double memberAmount = this.member.getAccountBalance();
        if(type == "deposit"){
            if(!this.memberRepository.existsByUsername(username)){

                double amount = this.memberRepository.findByUsername(username).getAccountBalance();
                if(transaction.getAmount() >= memberAmount ) {
                    this.memberRepository.updateMemberAccount((transaction.getAmount() - memberAmount), username);

                   // NjangiAccount njangiAccount = this.njangiAccountRepository.findByAccountNumber(njangiAc);
                    //double njangiAccountBalance = njangiAccount.getAmount();
                    this.njangiAccountRepository.updateAccount((njangiAmount + memberAmount), njangiAccount.get(0).getId());
                }

            }

        }
        else if(type == "withdraw"){

           // NjangiAccount njangiAccount = this.njangiAccountRepository.findByAccountNumber(account_number);

            if(!this.njangiAccountRepository.existsById(njangiAccount.get(0).getId())){

                // get the total amount in the njangi account
                double totalAmount = this.njangiAccount.getAmount();
                if(transaction.getAmount() <= totalAmount) {
                    this.njangiAccountRepository.updateAccount((totalAmount - transaction.getAmount()), njangiAccount.get(0).getId()); // subtract

                    Member member = this.memberRepository.findByUsername(username);
                    double memberAccountBalance = member.getAccountBalance();
                    this.memberRepository.updateMemberAccount((memberAccountBalance + transaction.getAmount()),member.getIdentifier());
                }
            }

        }
    transaction.setMember(memberRepository.findByUsername(username));
     transaction.setNjangiAccount(njangiAccount.get(0));

     transactionRepository.save(transaction);
     return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

    /**
     * Update member transaction object.
     *
     * @param
     * @return Transaction object (updated CustomerItem object).
     */
    @RequestMapping(value="/{transactionId}",
            method=RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction){

        Transaction transaction1 = transactionService.findById(transaction.getId());
        transaction.setAmount(transaction.getAmount());
        transaction.setDate(transaction.getDate());
        transaction1 = transactionService.update(transaction);
        if (transaction1 == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(transaction1, HttpStatus.OK);

    }




}
