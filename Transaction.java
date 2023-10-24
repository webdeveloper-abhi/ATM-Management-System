package Programmer.abhiprojectAtm;

public class Transaction {

    private String TransactionType;
    private double TransactionAmount;
    private String TransactionDate;
    private Account account;

    public Transaction(Account account, String TransactionType, double TransactionAmount, String TransactionDate) {
        this.account = account;
        this.TransactionType = TransactionType;
        this.TransactionAmount = TransactionAmount;
        this.TransactionDate = TransactionDate;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public double getTransactionAmount() {
        return TransactionAmount;
    }

    public String getAccountNumber() {
        return account.getAccountnumber();
    }

    @Override
    public String toString() {
        return "Transaction Type: " + TransactionType + " Transaction Amount: " + TransactionAmount + " Transaction Date: " + TransactionDate;
    }
}
