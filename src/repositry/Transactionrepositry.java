package repositry;

import domain.Transaction;

import java.util.*;

public class Transactionrepositry {
    private final Map<String, List<Transaction>> txnbyaccount = new HashMap<>();

    public void add(Transaction transaction) {
        List<Transaction> list = txnbyaccount.computeIfAbsent(transaction.getAccountnumber(),
                k -> new ArrayList<>());list.add(transaction);
        //if key is present it will give transaction details , but if not found it will give new arraylist
    }

    public List<Transaction> findByAccount(String account) {
        return new ArrayList<>(txnbyaccount.getOrDefault(account, Collections.emptyList()));

    }
}
