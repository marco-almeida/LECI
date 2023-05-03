package lab08.ex2;

public class BankAccountProxy implements BankAccount {
    private final BankAccount bank;

    public BankAccountProxy(BankAccount b) {
        this.bank = b;
    }

    @Override
    public void deposit(double amount) {
        this.bank.deposit(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (Company.user == User.COMPANY) {
            System.out.println("Operation denied: withdraw. User is not the owner.");
            return false;
        }
        return this.bank.withdraw(amount);
    }

    @Override
    public double balance() {
        if (Company.user == User.COMPANY) {
            System.out.println("Operation denied: balance. User is not the owner.");
            return Double.NaN;
        }
        return this.bank.balance();
    }
}
