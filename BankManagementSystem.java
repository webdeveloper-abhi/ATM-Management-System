package Programmer.abhiprojectAtm;

import java.util.ArrayList;
import java.util.Scanner;

public class BankManagementSystem {

    Scanner sc = new Scanner(System.in);

    private ArrayList<Transaction> transactionList;
    private ArrayList<Account> accounts;

    public BankManagementSystem() {
        transactionList = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Account getAccountByPin(String pin) {
        for (Account account : accounts) {
            if (account.getPin().equals(pin)) {
                return account;
            }
        }
        return null;
    }

    void addAccount() {
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = sc.nextLine();
        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();
        System.out.print("Enter Account Balance: ");
        double accountBalance = sc.nextDouble();
        System.out.print("Set Pin: ");
        sc.nextLine();
        String pin = sc.nextLine();


        Account newAccount = new Account(accountHolderName, accountNumber, accountBalance, pin);
        accounts.add(newAccount);
    }

    void accountDetails(String pin) {
        for (Account account : accounts) {
            if (account.getPin().equals(pin)) {
                System.out.println("Account Holder Name: " + account.getName());
                System.out.println("Account Number: " + account.getAccountnumber());
                System.out.println("Account Balance: " + account.getAccountbalance());
                return;
            }
        }
        System.out.println("Account not found.");
    }


    void TransactionHistory(Account account) {
        for (Transaction transaction : transactionList) {

            if (transaction.getAccountNumber().equals(account.getAccountnumber())) {
                System.out.println(transaction.toString());
            }
        }
    }

    void depositAmount(String accountNumber, double deposit) {
        System.out.print("Enter date: ");
        String date = sc.nextLine();

        if (deposit <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        for (Account account : accounts) {
            if (account.getAccountnumber().equals(accountNumber)) {
                double newBalance = account.getAccountbalance() + deposit;
                account.setAccountbalance(newBalance);
                Transaction transaction = new Transaction(account, "Deposit", deposit, date);
                transactionList.add(transaction);
                System.out.println("Deposit successful.");
                return;
            }
        }

        System.out.println("Account not found.");
    }

    void withdrawAmount(String accountNumber) {
        System.out.print("Enter date: ");
        String date = sc.nextLine();
        System.out.print("Enter Amount to Withdraw: ");
        double withdraw = sc.nextDouble();


        if (withdraw <= 0) {
            System.out.println("Invalid Withdraw");
            return;
        }

        for (Account account : accounts) {
            if (account.getAccountnumber().equals(accountNumber)) {
                double accountBalance = account.getAccountbalance();
                if (withdraw > accountBalance) {
                    System.out.print("Insufficient Funds");
                    return;
                }

                accountBalance -= withdraw;
                account.setAccountbalance(accountBalance);
                Transaction transaction = new Transaction(account, "Withdraw", withdraw, date);
                transactionList.add(transaction);
                System.out.println("Withdraw successful.");
                return;
            }
        }

        System.out.println("Account not found.");
    }

    void fundTransfer(String senderAccountNumber, String recipientAccountNumber, double amount) {
        System.out.print("Enter date: ");
        sc.nextLine();
        String date = sc.nextLine();

        double senderBalance = checkAccountBalance(senderAccountNumber);
        if (amount <= 0) {
            System.out.println("Invalid Transfer Amount");
            return;
        }

        if (amount > senderBalance) {
            System.out.println("Funds are insufficient to transfer the entered amount");
            return;
        }

        Account senderAccount = null;
        Account recipientAccount = null;

        for (Account account : accounts) {
            if (account.getAccountnumber().equals(senderAccountNumber)) {
                senderAccount = account;
            }
            if (account.getAccountnumber().equals(recipientAccountNumber)) {
                recipientAccount = account;
            }
        }

        if (senderAccount != null && recipientAccount != null) {
            double senderNewBalance = senderAccount.getAccountbalance() - amount;
            double recipientNewBalance = recipientAccount.getAccountbalance() + amount;

            senderAccount.setAccountbalance(senderNewBalance);
            recipientAccount.setAccountbalance(recipientNewBalance);

            Transaction senderTransaction = new Transaction(senderAccount, "Transfer", amount, date);
            Transaction recipientTransaction = new Transaction(recipientAccount, "Transfer", amount, date);
            transactionList.add(senderTransaction);
            transactionList.add(recipientTransaction);
            System.out.println("Funds transferred successfully.");
        } else {
            System.out.println("Transaction Failed! One or both of the accounts do not exist.");
        }
    }

    double checkAccountBalance(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountnumber().equals(accountNumber)) {
                return account.getAccountbalance();
            }
        }
        System.out.println("Account not found.");
        return -1;
    }

    public void closeAccount(Account accountToClose) {
        if (accountToClose != null) {
            accounts.remove(accountToClose);
            System.out.println("Account closed successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public Account switchAccount() {
        System.out.print("Enter PIN to switch to a different account: ");
        String newPin = sc.nextLine();

        Account newAccount = getAccountByPin(newPin);

        if (newAccount != null) {
            System.out.println("Switched to the new account.");
        } else {
            System.out.println("Account not found. Unable to switch.");
        }

        return newAccount;
    }



}
