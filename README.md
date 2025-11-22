# Banking_Application
Banking Application Project made in Java . Includes the important features of core java and OOPs concepts
Console Bank is a minimal console-based banking application implemented in Java. It provides a simple interactive menu to create accounts, deposit and withdraw money, transfer between accounts, print account statements, list all accounts and search accounts by customer name.

The main entry point is app.main which uses a BankService implementation to perform operations. The UI is text-based and intended for learning, demonstration, or extension.

Features

Create accounts (SAVINGS / CURRENT)

Initial optional deposit when opening account

Deposit money into an account

Withdraw money from an account

Transfer money between accounts

Print account statement (timestamp, type, amount, note)

List all accounts with basic details

Search accounts by (partial) customer name from CLI

Requirements

Java 11+ (or Java 8 compatible if you remove language features requiring newer JDKs)

A terminal / console

Source code for service.BankService and service.impl.BankServiceimpl (this project depends on these)

Project structure (summary)
src/
└── app/
    └── main.java           // console UI (provided)
└── service/
    └── BankService.java    // interface (expected)
└── service/impl/
    └── BankServiceimpl.java// implementation (expected)
└── model/                  // (optional) models like Account, Transaction


The provided main class expects a BankServiceimpl class (implementing BankService) with methods used in the UI. See the BankService interface section for details.
