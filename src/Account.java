// Base Account class
public abstract class Account {
    private String accountNumber;
    private double balance;
    private Client owner;

    public Account(String accountNumber, double initialBalance, Client owner) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.owner = owner;
    }

    // Common methods for all accounts
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Client getOwner() {
        return owner;
    }

    // Protected method to update balance (used by child classes)
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    // Abstract methods that subclasses must implement
    public abstract boolean deposit(double amount);
    public abstract boolean withdraw(double amount);
    public abstract String getAccountType();

    @Override
    public String toString() {
        return getAccountType() + " - Account #: " + accountNumber +
                ", Balance: $" + String.format("%.2f", balance);
    }
}

