package Programmer.abhiprojectAtm;

public class Account {

    private String name;
    private String Accountnumber;
    private double Accountbalance;
    private String pin;

    public Account(String name, String Accountnumber, double Accountbalance, String pin) {
        this.name = name;
        this.Accountnumber = Accountnumber;
        this.Accountbalance = Accountbalance;
        this.pin = pin;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public String getAccountnumber() {
        return Accountnumber;
    }

    public double getAccountbalance() {
        return Accountbalance;
    }

    public String getPin() {
        return pin;
    }

    // Setter method for updating account balance
    public void setAccountbalance(double newBalance) {
        this.Accountbalance = newBalance;
    }
}
