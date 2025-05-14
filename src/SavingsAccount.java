public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(double balance) {
        super(balance);
        interestRate = 0.01;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void addInterest() {
        double interest = getBalance() * interestRate;
        balance += interest;
    }

    public String toString() {
        return "Savings Account: " + getBalance() + " interest rate: " + interestRate;
    }




}
