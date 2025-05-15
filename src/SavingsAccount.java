
public class SavingsAccount extends Account {
    private static final double ANNUAL_INTEREST_RATE = 0.025;
    private static final int WITHDRAWAL_LIMIT = 6;

    private int withdrawalsThisMonth;

    public SavingsAccount(String accountNumber, double initialBalance, Client owner) {
        super(accountNumber, initialBalance, owner);
        this.withdrawalsThisMonth = 0;
    }

    public void applyMonthlyInterest() {
        double interest = getBalance() * (ANNUAL_INTEREST_RATE / 12);
        setBalance(getBalance() + interest);

        withdrawalsThisMonth = 0;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        setBalance(getBalance() + amount);
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || withdrawalsThisMonth >= WITHDRAWAL_LIMIT) {
            return false;
        }

        if (amount > getBalance()) {
            return false;
        }

        setBalance(getBalance() - amount);
        withdrawalsThisMonth++;
        return true;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    public int getRemainingWithdrawals() {
        return WITHDRAWAL_LIMIT - withdrawalsThisMonth;
    }
}
