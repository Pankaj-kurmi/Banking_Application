package domain;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private Type type;
    private String accountnumber;
    private double amount;
    private LocalDateTime timestamp;
    private String note;

    public Transaction(String id, double amount, String accountnumber, String note , LocalDateTime timestamp,Type type) {
        this.id = id;
        this.type = type;
        this.accountnumber = accountnumber;
        this.amount = amount;
        this.timestamp = timestamp;
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
