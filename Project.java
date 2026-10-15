import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Project {

    static class AccountInfo {
        private String name;
        private String accountNumber;

        public AccountInfo(String name, String accountNumber) {
            this.name = name;
            this.accountNumber = accountNumber;
        }

        public String getName() {
            return name;
        }

        public String getAccountNumber() {
            return accountNumber;
        }
    }

    static ArrayList<String> transactions = new ArrayList<>();

    static String[] accountNumbers = {
        "2620090077",
        "2620030330",
        "2620030392",
        "2620030291",
        "2620030410"
    };

    public static boolean checkAccount(String accountNumber) {
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumber.equals(accountNumbers[i])) {
                return true;
            }
        }
        return false;
    }

    public static void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }

        System.out.println("\n---------- TRANSACTION HISTORY ----------");
        transactions.forEach(transaction -> System.out.println(transaction));
    }

    public static void saveTransactions() {
        try (FileWriter writer = new FileWriter("bank_statement.txt")) {
            for (String transaction : transactions) {
                writer.write(transaction + System.lineSeparator());
            }
            System.out.println("Statement saved to bank_statement.txt");
        } catch (IOException e) {
            System.out.println("Unable to save statement.");
        }
    }

    public static void readTransactions() {
        File file = new File("bank_statement.txt");

        if (!file.exists()) {
            System.out.println("No saved statement found. Generate a statement first.");
            return;
        }

        System.out.println("\n---------- SAVED TRANSACTIONS ----------");
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Unable to read statement.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String name;
        String accountNumber;
        int age;
        double balance;

        double deposit;
        double withdraw;
        double totalDeposit = 0;
        double totalWithdraw = 0;

        System.out.println("====================================");
        System.out.println("       PERSONAL BANK LEDGER");
        System.out.println("====================================");

        System.out.print("Enter your name: ");
        name = sc.nextLine();

        System.out.print("Enter your account number: ");
        accountNumber = sc.nextLine();

        if (checkAccount(accountNumber)) {

            System.out.println("\nAccount verified successfully!");
            System.out.println("Welcome, " + name + "!");

        } else {

            System.out.println("\nInvalid account number!");
            System.out.println("Access denied.");
            System.out.println("You are not an authorized account holder.");

            sc.close();
            return;
        }

        AccountInfo account = new AccountInfo(name, accountNumber);
        name = account.getName();
        accountNumber = account.getAccountNumber();

        try {
            System.out.print("Enter your age: ");
            age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter your initial balance: ");
            balance = Double.parseDouble(sc.nextLine());

            if (age < 0 || balance < 0) {
                System.out.println("Age and balance cannot be negative.");
                sc.close();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numbers for age and balance.");
            sc.close();
            return;
        }

        if (age >= 18) {
            System.out.println("\nWelcome, " + name + "!");
        } else {
            System.out.println("\nWelcome, " + name + "!");
            System.out.println("Note: You are a minor account holder.");
        }

        transactions.add("Opening balance: Rs." + balance);

        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("              BANK MENU");
            System.out.println("====================================");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Generate Bank Statement");
            System.out.println("5. View Transaction History");
            System.out.println("6. Read Saved Statement");
            System.out.println("7. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Please enter a number.");
                choice = 0;
                continue;
            }

            switch (choice) {

                case 1:
                    try {
                        System.out.print("Enter amount to deposit: ");
                        deposit = Double.parseDouble(sc.nextLine());

                        if (deposit > 0) {
                            balance = balance + deposit;
                            totalDeposit = totalDeposit + deposit;

                            transactions.add("Deposit: Rs." + deposit +
                                    " | Balance: Rs." + balance);

                            System.out.println("Deposit successful!");
                            System.out.println("Amount deposited: Rs." + deposit);
                            System.out.println("Current balance: Rs." + balance);
                        } else {
                            System.out.println("Invalid amount!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Enter a numeric amount.");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter amount to withdraw: ");
                        withdraw = Double.parseDouble(sc.nextLine());

                        if (withdraw > 0 && withdraw <= balance) {
                            balance = balance - withdraw;
                            totalWithdraw = totalWithdraw + withdraw;

                            transactions.add("Withdrawal: Rs." + withdraw +
                                    " | Balance: Rs." + balance);

                            System.out.println("Withdrawal successful!");
                            System.out.println("Amount withdrawn: Rs." + withdraw);
                            System.out.println("Current balance: Rs." + balance);
                        } else if (withdraw > balance) {
                            System.out.println("Insufficient balance!");
                        } else {
                            System.out.println("Invalid amount!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Enter a numeric amount.");
                    }
                    break;

                case 3:
                    System.out.println("\n---------- ACCOUNT BALANCE ----------");
                    System.out.println("Customer Name : " + name);
                    System.out.println("Account Number: " + accountNumber);
                    System.out.println("Current Balance: Rs." + balance);
                    break;

                case 4:
                    System.out.println("\n====================================");
                    System.out.println("          BANK STATEMENT");
                    System.out.println("====================================");
                    System.out.println("Customer Name  : " + name);
                    System.out.println("Account Number : " + accountNumber);
                    System.out.println("Age            : " + age);
                    System.out.println("------------------------------------");
                    System.out.println("Total Deposited  : Rs." + totalDeposit);
                    System.out.println("Total Withdrawn  : Rs." + totalWithdraw);
                    System.out.println("Current Balance  : Rs." + balance);

                    showTransactions();
                    saveTransactions();

                    System.out.println("====================================");
                    break;

                case 5:
                    showTransactions();
                    break;

                case 6:
                    readTransactions();
                    break;

                case 7:
                    System.out.println("\nThank you for banking with us, " + name + "!");
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1 to 7.");
            }

        } while (choice != 7);

        sc.close();
    }
}