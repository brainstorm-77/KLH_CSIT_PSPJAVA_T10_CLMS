import java.util.Scanner;

public class PersonalBank {
      static String[] accountNumbers = {
        "2620090077",
        "2620030330",
        "2620030392",
        "2620030291",
        "2620090333"
    };
  public static boolean checkAccount(String accountNumber) {
        for (int i = 0; i < accountNumbers.length; i++) {
if (accountNumber.equals(accountNumbers[i])) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);

        // Customer details
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

        System.out.print("Enter your age: ");
        age = sc.nextInt();
        System.out.print("Enter your initial balance: ");
        balance = sc.nextDouble();

        if (age >= 18) {
            System.out.println("\nWelcome, " + name + "!");
        } else {
            System.out.println("\nWelcome, " + name + "!");
            System.out.println("Note: You are a minor account holder.");
        }
        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("              BANK MENU");
            System.out.println("====================================");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Generate Bank Statement");
            System.out.println("5. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    deposit = sc.nextDouble();
                    if (deposit > 0) {
                        balance = balance + deposit;
                        totalDeposit = totalDeposit + deposit;
                        System.out.println("Deposit successful!");
                        System.out.println("Amount deposited: Rs." + deposit);
                        System.out.println("Current balance: Rs." + balance);
                    } else {
                        System.out.println("Invalid amount!");
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    withdraw = sc.nextDouble();
                    if (withdraw > 0 && withdraw <= balance) {
                        balance = balance - withdraw;
                        totalWithdraw = totalWithdraw + withdraw;
                        System.out.println("Withdrawal successful!");
                        System.out.println("Amount withdrawn: Rs." + withdraw);
                        System.out.println("Current balance: Rs." + balance);
                    } else if (withdraw > balance) {
                        System.out.println("Insufficient balance!");
                    } else {
                        System.out.println("Invalid amount!");
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
                    System.out.println("====================================");
                    break;

                case 5:
                    System.out.println("\nThank you for banking with us, " + name + "!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1 to 5.");
            }

        } while (choice != 5);

        sc.close();
    }
}
