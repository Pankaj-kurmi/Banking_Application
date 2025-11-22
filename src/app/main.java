package app;

import service.BankService;
import service.impl.BankServiceimpl;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Console Bank ");
        BankService bankService = new BankServiceimpl();
       boolean run = true;
       while(run){
           Scanner sc = new Scanner(System.in);

           // Showing options in the console
           System.out.println("""
                1. Create an account
                2. Deposit money
                3. Withdraw money
                4. Transfer money
                5. Account Statement
                6. List Accounts
                7. Search Account by customer name
                8. Exit
                
                """);
           System.out.println("Enter your choice: ");
           String choice = sc.nextLine().trim();


           // using switch statement to handle multiple cases
           switch (choice){

               case "1" : openAccount(sc , bankService); break;
               case "2" : deposit(sc , bankService);  break;
               case "3" : withdraw(sc, bankService); break;
               case "4" : transfer(sc, bankService); break;
               case "5" : statement(sc, bankService); break;
               case "6" : listsAccounts(sc , bankService); break;
               case "7" : searchAccount(sc, bankService); break;
               case "8" : run = false;



           }
       }


    }
    private static void openAccount(Scanner sc  ,BankService bankService){
        System.out.println("Enter customer name: ");
        String customername = sc.nextLine().trim();
        System.out.println("Enter customer email id:");
        String email = sc.nextLine().trim();
        System.out.println("Enter account type (SAVINGS/CURRENT):");
        String accountType = sc.nextLine().trim();
        System.out.println("Initial deposit: (Optional/ blank for 0)");
        String deposit = sc.nextLine().trim();
        Double initaldeposit = Double.valueOf(deposit);
        String accountnumber = bankService.openAccount(customername , email,accountType);
        if (initaldeposit>0){
            bankService.deposit(accountnumber,initaldeposit,"Initial deposit");
            System.out.println("Account opened: " + accountnumber);
       }



    }
    private static void deposit(Scanner sc  ,BankService bankService){
        System.out.println("DEPOSIT RUNNING");
        System.out.println("Enter accountNUmber :");
        String accountnumber = sc.nextLine().trim();
        System.out.println("Enter amount to deposit:");
        double amount = Double.parseDouble(sc.nextLine().trim());
        bankService.deposit(accountnumber , amount , "Deposit");
        System.out.println("Amount deposited succesfully:");

    }
    private static void withdraw(Scanner sc  ,BankService bankService){
        System.out.println("Enter accountNUmber :");
        String accountnumber = sc.nextLine().trim();
        System.out.println("Enter amount to withdrwa:");
        double amount = Double.valueOf(sc.nextLine().trim());
        bankService.withdraw(accountnumber , amount , "withdrawl");
        System.out.println("Amount withdraw succesfully:");

    }
    private static void transfer(Scanner sc  ,BankService bankService){
        System.out.println("From Account :");
        String from = sc.nextLine().trim();
        System.out.println("TO Account:");
        String To = sc.nextLine().trim();
        System.out.println("amount");
        double amount = Double.valueOf(sc.nextLine().trim());
        bankService.transfer(from , To ,amount ,"transfered");

    }
    private static void statement(Scanner sc  ,BankService bankService){
        System.out.println("Enter account number");
        String account = sc.nextLine().trim();
        bankService.getStatement(account).forEach(t->
                System.out.println(t.getTimestamp()+ " | " + t.getType()+ " | " + t.getAmount()
                +" |" + t.getNote()));

    }
    private static void listsAccounts(Scanner sc ,BankService bankService){
        bankService.listsAccounts().forEach(a ->
                System.out.println(a.getAccountnumber()+ " | " + a.getAccounttype()
                        + " | " + a.getBalance()));

    }
    private static void searchAccount(Scanner sc  ,BankService bankService){
        System.out.println("customer name contains: ");
        String q = sc.nextLine().trim();
        bankService.searchAccount(q).forEach(
                account ->
                        System.out.println(account.getAccountnumber()+ " | "+
                                account.getAccounttype()+ " | "+account.getBalance())
        );
    }
}
