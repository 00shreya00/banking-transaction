import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class BankingSystem {
    private Map<String, Account> accounts;
    private Scanner scanner;

    public BankingSystem() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolder = scanner.nextLine();
        
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists.");
        } else {
            Account newAccount = new Account(accountNumber, accountHolder);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created successfully.");
        }
    }

    public void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);

        if (account != null) {
            try {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (amount <= 0) {
                    System.out.println("Amount must be positive.");
                } else {
                    account.deposit(amount);
                    System.out.println("Deposit successful.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid amount. Please enter a numeric value.");
                scanner.nextLine(); // Clear the buffer
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);

        if (account != null) {
            try {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (amount <= 0) {
                    System.out.println("Amount must be positive.");
                } else if (amount > account.getBalance()) {
                    System.out.println("Insufficient funds.");
                } else {
                    account.withdraw(amount);
                    System.out.println("Withdrawal successful.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid amount. Please enter a numeric value.");
                scanner.nextLine(); // Clear the buffer
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);

        if (account != null) {
            System.out.println("Account Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer() {
        System.out.print("Enter source account number: ");
        String sourceAccountNumber = scanner.nextLine();
        Account sourceAccount = accounts.get(sourceAccountNumber);

        if (sourceAccount != null) {
            System.out.print("Enter destination account number: ");
            String destinationAccountNumber = scanner.nextLine();
            Account destinationAccount = accounts.get(destinationAccountNumber);

            if (destinationAccount != null) {
                try {
                    System.out.print("Enter amount to transfer: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    if (amount <= 0) {
                        System.out.println("Amount must be positive.");
                    } else if (amount > sourceAccount.getBalance()) {
                        System.out.println("Insufficient funds.");
                    } else {
                        sourceAccount.transfer(destinationAccount, amount);
                        System.out.println("Transfer successful.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid amount. Please enter a numeric value.");
                    scanner.nextLine(); // Clear the buffer
                }
            } else {
                System.out.println("Destination account not found.");
            }
        } else {
            System.out.println("Source account not found.");
        }
    }

    public void start() {
        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Money");
            System.out.println("6. Exit");

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a numeric value.");
                scanner.nextLine(); // Clear the buffer
                continue;
            }

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    transfer();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        bankingSystem.start();
    }
}
