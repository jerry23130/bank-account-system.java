class BankAccount {
    protected double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }

    public double checkBalance() {
        return balance;
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
        this.interestRate = interestRate;
    }

    public void addInterest(int periodInMonths) {
        double interest = balance * interestRate * periodInMonths / 12;
        balance += interest;
        System.out.println("Interest added: $" + interest);
    }
}

class CheckingAccount extends BankAccount {
    private double fee;
    private double minimumBalance;

    public CheckingAccount(double initialBalance, double fee, double minimumBalance) {
        super(initialBalance);
        this.fee = fee;
        this.minimumBalance = minimumBalance;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            balance -= fee; // Charge the fee
            System.out.println("Withdrew: $" + amount + ", Fee: $" + fee);
            if (balance < minimumBalance) {
                System.out.println("Warning: Balance below minimum.");
            }
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount(1000, 0.05); // 5% interest
        savings.deposit(500);
        savings.addInterest(3); // Add 3 months' interest
        System.out.println("Savings balance: $" + savings.checkBalance());

        CheckingAccount checking = new CheckingAccount(2000, 2, 1000); // $2 fee, $1000 minimum
        checking.withdraw(100);
        checking.withdraw(1500);
        System.out.println("Checking balance: $" + checking.checkBalance());
    }
}
