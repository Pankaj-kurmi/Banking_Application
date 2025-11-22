package domain;

public class Account {
    private String accountnumber;
    private String customerid;
    private double balance;
    private String accounttype;

    public Account(String accountnumber, String customerid, double balance, String accounttype) {
        this.accountnumber = accountnumber;
        this.customerid = customerid;
        this.balance = balance;
        this.accounttype = accounttype;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
}
