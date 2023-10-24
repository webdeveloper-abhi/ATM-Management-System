package Programmer.abhiprojectAtm;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankManagementSystem atm = new BankManagementSystem();

        System.out.println("B A N K       O F             M A H A R A S H T R A \n ");
        System.out.println("One Family One Bank\n");


        System.out.println("Enter Account details");
        for (int i = 0; i < 2; i++) {
            atm.addAccount();
        }

        System.out.println("Enter PIN To login your Account");

        String pin=getPin();


        Account account = atm.getAccountByPin(pin);

        if (account == null) {
            System.out.println("Invalid PIN. Exiting...");
            return;
        }

        while (true) {
            menu();

            System.out.print("\nEnter Your Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    atm.accountDetails(pin);
                    break;

                case 2:
                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    atm.depositAmount(account.getAccountnumber(), amount);
                    break;

                case 3:
                    atm.withdrawAmount(account.getAccountnumber());
                    break;

                case 4:
                    System.out.println(atm.checkAccountBalance(account.getAccountnumber()));
                    break;

                case 5:
                    System.out.print("Enter Recipient Account Number: ");
                    String recipientAccountNumber = sc.next();
                    System.out.print("Enter Amount to Transfer: ");
                    double transferAmount = sc.nextDouble();
                    atm.fundTransfer(account.getAccountnumber(), recipientAccountNumber, transferAmount);
                    break;

                case 6:
                    Account newAccount = atm.switchAccount();
                    if (newAccount != null) {
                        account = newAccount;
                    }
                    break;

                case 7:
                    atm.addAccount();
                    break;

                case 8:
                    atm.closeAccount(account);
                    break;

                case 9:
                    atm.TransactionHistory(account);
                    break;

                case 10:
                    return;

                default:
                    System.out.println("Invalid Choice! Please Try Again");
            }
        }
    }

    static void menu() {
        System.out.println("Choose :");
        System.out.println("1->Account Details");
        System.out.println("2->Deposit Amount");
        System.out.println("3->Withdraw Amount");
        System.out.println("4->Check Account Balance");
        System.out.println("5->Fund Transfer");
        System.out.println("6->Switch Account");
        System.out.println("7->Create Another Bank Account");
        System.out.println("8->Close Bank Account");
        System.out.println("9->Transaction History");
        System.out.println("10->Exit");
    }

    static String getPin(){

        Scanner sc=new Scanner(System.in);
        System.out.print("Enter PIN: ");
       String pin=sc.nextLine();
       return pin;
    }
}
