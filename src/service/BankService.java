package service;

import domain.Account;
import domain.Transaction;

import java.util.List;

public interface BankService {
    String openAccount(String customername , String email , String accountType);
    List<Account> listsAccounts();

    void deposit(String accountnumber, double amount, String note);

    void withdraw(String accountnumber, double amount, String withdrawl);

    void transfer(String from, String to, double amount, String transfered);

    List<Transaction> getStatement(String account);

    List<Account> searchAccount(String q);
}
