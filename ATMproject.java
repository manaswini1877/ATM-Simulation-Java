import java.util.*;

public class ATMproject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATMSystem atm = new ATMSystem();

        System.out.println("****Welcome to Advanced ATM Simulator****");

        // PIN verification
        User currentUser = null;
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Enter your ATM PIN number: ");
            String enteredPin = sc.next();
            currentUser = atm.verifyPIN(enteredPin);
            if (currentUser != null) break;
            System.out.println("Incorrect PIN. Try again.");
        }

        if (currentUser == null) {
            System.out.println("Too many incorrect attempts. Exiting...");
            sc.close();
            return;
        }

        int choice;
        do {
            System.out.println("\n1. Balance enquiry\n2. Deposit money\n3. Withdraw money\n4. Transaction history\n5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 : currentUser.balanceEnquiry();
                         break;
                case 2 : 
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        currentUser.depositMoney(depositAmount);
                        break;
                case 3 : 
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        currentUser.withdrawMoney(withdrawAmount);
                        break;
                case 4 :currentUser.showTransactionHistory();
                        break;
                case 5 : System.out.println("Thanks for using!!");
                         break;
                default :System.out.println("Invalid choice! Try again.");
                        break;
            }
        } while (choice != 5);

        sc.close();
    }
}

// Class representing the ATM system with multiple users
class ATMSystem {
    private final List<User> users;

    public ATMSystem() {
        users = new ArrayList<>();
        // Adding some sample users
        users.add(new User("Manaswini", "1234", 5000));
        users.add(new User("Suni", "5678", 10000));
        users.add(new User("Chaitanya", "9876", 7500));
    }

    public User verifyPIN(String pin) {
        for (User u : users) {
            if (u.getPin().equals(pin)) 
                return u;
        }
        return null;
    }
}

// Class representing a bank user
class User {
    private final String name;
    private final String pin;
    private double balance;
    private final List<String> transactionHistory;

    public User(String name, String pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getPin() {
        return pin;
    }
    public void balanceEnquiry() {
        System.out.printf("Hello %s, your available balance is: %.2f\n", name, balance);
    }
    public void depositMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("**Successfully credited**");
        } else {
            System.out.println("Please enter a valid amount to deposit.");
        }
    }
    public void withdrawMoney(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
            System.out.println("**Successfully debited**");
        }
    }
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String t : transactionHistory) {
                System.out.println(t);
            }
        }
    }
}
