
import java.util.Scanner;
import java.util.InputMismatchException;

// Base BankAccount Class
class BankAccount {
    protected double balance; // Account balance

    // Constructor
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }

    // Check balance method
    public double checkBalance() {
        return balance;
    }
}

// SavingsAccount Subclass
class SavingsAccount extends BankAccount {
    private double interestRate; // Annual interest rate

    // Constructor
    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
        this.interestRate = interestRate;
    }

    // Method to add interest for a specified period in months
    public void addInterest(int periodInMonths) {
        double interest = balance * interestRate * periodInMonths / 12;
        balance += interest;
        System.out.println("Interest added: $" + interest);
    }
}

// CheckingAccount Subclass
class CheckingAccount extends BankAccount {
    private double fee;            // Withdrawal fee
    private double minimumBalance; // Minimum balance threshold

    // Constructor
    public CheckingAccount(double initialBalance, double fee, double minimumBalance) {
        super(initialBalance);
        this.fee = fee;
        this.minimumBalance = minimumBalance;
    }

    // Override withdraw method to include fees and check minimum balance
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount + fee <= balance) {
            balance -= amount;
            balance -= fee; // Deduct fee
            System.out.println("Withdrew: $" + amount + ", Fee: $" + fee);

            // Check if balance is below the minimum balance
            if (balance < minimumBalance) {
                System.out.println("Warning: Balance below minimum.");
            }
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create SavingsAccount and CheckingAccount
        SavingsAccount savings = new SavingsAccount(1000, 0.05); // Initial balance: $1000, 5% interest
        CheckingAccount checking = new CheckingAccount(2000, 2, 1000); // Initial balance: $2000, $2 fee, $1000 minimum

        while (true) {
            try {
                // Menu for user interaction
                System.out.println("\n--- Bank Account System ---");
                System.out.println("1. Deposit to Savings");
                System.out.println("2. Withdraw from Savings");
                System.out.println("3. Add Interest to Savings");
                System.out.println("4. Deposit to Checking");
                System.out.println("5. Withdraw from Checking");
                System.out.println("6. Check Balances");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: // Deposit to Savings
                        System.out.print("Enter deposit amount for Savings: ");
                        double savingsDeposit = scanner.nextDouble();
                        savings.deposit(savingsDeposit);
                        break;
                    case 2: // Withdraw from Savings
                        System.out.print("Enter withdrawal amount for Savings: ");
                        double savingsWithdraw = scanner.nextDouble();
                        savings.withdraw(savingsWithdraw);
                        break;
                    case 3: // Add Interest to Savings
                        System.out.print("Enter period in months for interest: ");
                        int months = scanner.nextInt();
                        if (months > 0) {
                            savings.addInterest(months);
                        } else {
                            System.out.println("Invalid period. Please enter a positive number.");
                        }
                        break;
                    case 4: // Deposit to Checking
                        System.out.print("Enter deposit amount for Checking: ");
                        double checkingDeposit = scanner.nextDouble();
                        checking.deposit(checkingDeposit);
                        break;
                    case 5: // Withdraw from Checking
                        System.out.print("Enter withdrawal amount for Checking: ");
                        double checkingWithdraw = scanner.nextDouble();
                        checking.withdraw(checkingWithdraw);
                        break;
                    case 6: // Check Balances
                        System.out.println("Savings Account Balance: $" + savings.checkBalance());
                        System.out.println("Checking Account Balance: $" + checking.checkBalance());
                        break;
                    case 7: // Exit
                        System.out.println("Exiting. Thank you for using the Bank Account System!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
