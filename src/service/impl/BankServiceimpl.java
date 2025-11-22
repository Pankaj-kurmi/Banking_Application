package service.impl;

import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import exceptions.AccountNotFound;
import exceptions.InsufficientFundException;
import exceptions.ValidationException;
import repositry.AccountRepositry;
import repositry.CustomerRepositry;
import repositry.Transactionrepositry;
import service.BankService;
import util.util.Validation;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BankServiceimpl implements BankService {
    private  final AccountRepositry accountRepositry = new AccountRepositry();

    private final Transactionrepositry transactionrepositry = new Transactionrepositry();

    private final CustomerRepositry customerRepositry = new CustomerRepositry();

    private  final Validation<String> validateName = name ->{
        if (name == null || name.isBlank()) throw  new ValidationException("Name is Required");
    };

    private  final Validation<String> validateemail = email ->{
        if (email == null || !email.contains("@")) throw  new ValidationException("Email is Required");
    };

    private  final Validation<String> validateType = type ->{
        if (type == null || !(type.equalsIgnoreCase("SAVINGS")|| type.equalsIgnoreCase("CURRENT")))
            throw  new ValidationException("Type must be savings or current");
    };




    @Override
    public String openAccount(String customername, String email, String accountType) {
        validateName.valid(customername);
        validateemail.valid(email);
        validateType.valid(accountType);
        String customerid = UUID.randomUUID().toString();

        //customer id

        Customer c = new Customer(email,customerid,customername);
        CustomerRepositry.save(c);

       // change according to no. of accoun in server like 10+1-> AC11
        String accountnumber = getAccountNUmber();

        Account account = new Account(accountnumber,customerid,0,accountType);

        // save account information
        accountRepositry.save(account);

        return accountnumber;
    }

    @Override
    public List<Account> listsAccounts() {
        return accountRepositry.findAll().stream().
                sorted(Comparator.comparing(Account::getAccountnumber))
                .collect(Collectors.toList());
    }

    @Override
    public void deposit(String accountnumber, double amount, String note) {
        Account account = accountRepositry.findByNumber(accountnumber).orElseThrow(
                ()-> new AccountNotFound("ACCOUNT NOT FOUND")
        );
        account.setBalance(account.getBalance()+amount);
        Transaction transaction = new Transaction(account.getAccountnumber(), amount,UUID.randomUUID().toString()
                ,note, LocalDateTime.now(), Type.DEPOSIT);
        transactionrepositry.add(transaction);
    }

    @Override
    public void withdraw(String accountnumber, double amount, String note) {
        Account account = accountRepositry.findByNumber(accountnumber).orElseThrow(
                ()-> new AccountNotFound("ACCOUNT NOT FOUND")
        );
        if(account.getBalance() < amount){
            throw new InsufficientFundException("Insufficient balance");
        }
        account.setBalance(account.getBalance()-amount);
        Transaction transaction = new Transaction(account.getAccountnumber(), amount,UUID.randomUUID().toString()
                ,note, LocalDateTime.now(), Type.WITHDRAW);
        transactionrepositry.add(transaction);
    }

    @Override
    public void transfer(String fromAcc, String toAcc, double amount, String note) {
        if (fromAcc.equals(toAcc) ){
            throw new ValidationException("can not transfer to same account");
        }
        Account from = accountRepositry.findByNumber(fromAcc).orElseThrow(
                ()-> new AccountNotFound("ACCOUNT NOT FOUND")
        );
        Account to = accountRepositry.findByNumber(toAcc).orElseThrow(
                ()-> new AccountNotFound("ACCOUNT NOT FOUND")
        );
        if(from.getBalance() < amount){
            throw new InsufficientFundException("Insufficient balance");

        } from.setBalance(from.getBalance()- amount);
        to.setBalance(to.getBalance()+amount);
        Transaction fromtransaction = new Transaction(from.getAccountnumber(), amount,UUID.randomUUID().toString()
                ,note, LocalDateTime.now(), Type.TRANSFER_OUT);
        transactionrepositry.add(fromtransaction);
        Transaction toransaction = new Transaction(to.getAccountnumber(), amount,UUID.randomUUID().toString()
                ,note, LocalDateTime.now(), Type.TRANSFER_IN);
        transactionrepositry.add(toransaction);

    }

    @Override
    public List<Transaction> getStatement(String account) {
        return transactionrepositry.findByAccount(account).stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccount(String q) {
        String query = (q == null)? "" : q.toLowerCase();
       return customerRepositry.findAll().stream()
               .filter(c -> c.getName().toLowerCase().contains(query))
               .flatMap(c -> accountRepositry.findByCustomerId(c.getId()).stream())
               .sorted(Comparator.comparing(Account::getAccountnumber))
               .collect(Collectors.toList());

    }


    private String getAccountNUmber() {
        int size = accountRepositry.findAll().size()+1;
        return String.format("AC%06d" , size);
    }
}
