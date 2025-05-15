
public class CheckingAccount extends Account {
    private static final double OVERDRAFT_FEE = 35.0;
    private static final double OVERDRAFT_LIMIT = -100.0;

    public CheckingAccount(String accountNumber, double initialBalance, Client owner) {
        super(accountNumber, initialBalance, owner);
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
        if (amount <= 0) {
            return false;
        }

        double newBalance = getBalance() - amount;

        if (newBalance < OVERDRAFT_LIMIT) {
            return false;
        }


        if (newBalance < 0 && getBalance() >= 0) {
            newBalance -= OVERDRAFT_FEE;
        }

        setBalance(newBalance);
        return true;
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}
